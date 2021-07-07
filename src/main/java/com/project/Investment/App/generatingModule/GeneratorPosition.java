package com.project.Investment.App.generatingModule;

import com.project.Investment.App.model.Position;
import com.project.Investment.App.model.embeddedId.PositionId;
import com.project.Investment.App.util.math.BigFunctions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;

/**
 * The class generates data for the Position model
 *
 * @author Andriy Hishchak
 */
@Component
@Slf4j
public class GeneratorPosition {

    /**
     * The method generates data for the Position model.
     *
     * @param startDate date point from which data generation begins.
     * @param entityId  unique identifier
     * @param total     amount that is distributed between groups and subgroups.
     * @param group     an array containing the number of groups and consisting of the number of subgroups and the number of records for the subgroup.
     * @return list of positions
     */

    public List<Position> generatorPositions(LocalDate startDate, String entityId, int total, Integer[][] group) {
        List<Position> positions = new ArrayList<>();
        Map<Integer, Integer> securityIdAndAggregateID = generatorMapGroup(group);
        Double[] StartBmv = getDistributionBmv(securityIdAndAggregateID, total);
        LocalDate finishDate = startDate.plusMonths(3);

        for (int i = 0, securityId = 1; i < securityIdAndAggregateID.size(); i++, securityId++) {

            Double bmvGross = StartBmv[i];
            Double emvGross = getEmvGross(bmvGross);
            Position position = Position.builder()
                    .positionId(new PositionId(
                            entityId,
                            startDate,
                            securityId,
                            securityIdAndAggregateID.get(securityId)))
                    .frequency('D')
                    .weight(getWeight(bmvGross, (double) total))
                    .bmvGross(bmvGross)
                    .emvGross(emvGross)
                    .grossReturn(getReturnGross(bmvGross, emvGross))
                    .gainLossGross(getGainLossGross(emvGross, bmvGross))
                    .build();
            positions.add(position);
        }

        for (LocalDate date = startDate.plusDays(1); date.isBefore(finishDate); date = date.plusDays(1)) {

            if (isBusinessDay(date)) {

                int firstPosition = positions.size() - securityIdAndAggregateID.size();
                Double yesterdayTotal = getYesterdayTotal(positions, firstPosition);

                for (int i = 0, securityId = 1, firstIndexPositionForPreviousDay = firstPosition; i < securityIdAndAggregateID.size(); i++, securityId++, firstIndexPositionForPreviousDay++) {

                    Double bmvGross = positions.get(firstIndexPositionForPreviousDay).getEmvGross();
                    Double emvGross = getEmvGross(bmvGross);

                    Position position = Position.builder()
                            .positionId(new PositionId(
                                    entityId,
                                    date,
                                    securityId,
                                    securityIdAndAggregateID.get(securityId)))
                            .frequency('D')
                            .weight(getWeight(bmvGross, yesterdayTotal))
                            .bmvGross(bmvGross)
                            .emvGross(emvGross)
                            .grossReturn(getReturnGross(bmvGross, emvGross))
                            .gainLossGross(getGainLossGross(emvGross, bmvGross))
                            .build();
                    positions.add(position);
                }
            }
        }
        log.info("Method: generatorPosition - {} Position found", positions.size());
        return positions;
    }

    /**
     * The method checks whether the specified day is a day off.
     *
     * @param date the verification date
     * @return isBusinessDay
     */
    private boolean isBusinessDay(LocalDate date) {
        return date.getDayOfWeek().getValue() < 6;
    }

    /**
     * Method Counts the number of groups and subgroups.
     *
     * @param group an array containing the number of groups and consisting of the number of subgroups and the number of records for the subgroup.
     * @return Map of groups and subgroups
     */
    private static Map<Integer, Integer> generatorMapGroup(Integer[][] group) {
        Map<Integer, Integer> securityIdAndAggregateID = new HashMap<>();

        int countSubgroup;
        int countRecords;
        int indexMap = 1;
        int firstAggregate = 3;

        for (Integer[] integers : group) {
            countSubgroup = integers[0];

            for (int indexSubgroup = 0; indexSubgroup < countSubgroup; indexSubgroup++) {
                countRecords = integers[1];

                for (int indexRecord = 0; indexRecord < countRecords; indexRecord++) {
                    securityIdAndAggregateID.put(indexMap++, firstAggregate);
                }
                firstAggregate++;
            }
            firstAggregate++;
        }
        return securityIdAndAggregateID;
    }

    /**
     * The method calculates yesterday's total groups and subgroups.
     *
     * @param positions     list of all Positions
     * @param firstSecurity index of the first securityId
     * @return yesterday total
     */
    private Double getYesterdayTotal(List<Position> positions, Integer firstSecurity) {
        return positions.stream().skip(firstSecurity).map(Position::getEmvGross).reduce(Double::sum).get();
    }

    /**
     * The method counts the number of groups and subgroups.
     *
     * @param securityIdAndAggregateID map of groups and subgroups
     * @return Map of groups and subgroups
     */
    public Map<Integer, Integer> getCountSecurityIdAndAggregateID(Map<Integer, Integer> securityIdAndAggregateID) {
        Integer[] arr = securityIdAndAggregateID.values().toArray(new Integer[0]);
        Map<Integer, Long> countSecurityIdAndAggregateID;
        Map<Integer, Integer> newSecurityIdAndAggregateID = new HashMap<>();
        int indexSecurityIdAndAggregateID = 0;

        countSecurityIdAndAggregateID =
                Arrays.stream(arr)
                        .collect(Collectors.groupingBy(
                                Function.identity(),
                                counting()));

        for (int i = 0; i < arr.length; i++) {
            if (countSecurityIdAndAggregateID.containsKey(i)) {
                newSecurityIdAndAggregateID.put(indexSecurityIdAndAggregateID, Math.toIntExact(countSecurityIdAndAggregateID.get(i)));
                indexSecurityIdAndAggregateID++;
            }
        }
        log.info("Method: getCountSecurityIdAndAggregateID - get count securityId and aggregateId {} ", newSecurityIdAndAggregateID.toString());
        return newSecurityIdAndAggregateID;
    }

    /**
     * The method calculates gross return.
     *
     * @param bmvGross the begin market value
     * @param emvGross the end market value
     * @return return gross
     */
    private Double getReturnGross(Double bmvGross, Double emvGross) {
        if (BigFunctions.equals(emvGross, 0.0)) {
            return 0.0;
        }
        return BigFunctions.multiply(BigFunctions.divide(BigFunctions.subtract(emvGross, bmvGross), bmvGross), 100.0);
    }

    /**
     * The method calculates weight.
     *
     * @param bmvGross the begin market value
     * @param total    amount that is distributed between groups and subgroups.
     * @return weight
     */
    private Double getWeight(Double bmvGross, Double total) {
        return BigFunctions.multiply(BigFunctions.divide(bmvGross, total), 100.0);
    }

    /**
     * The method calculates gain loss gross.
     *
     * @param bmvGross the begin market value
     * @param emvGross the end market value
     * @return gain loss gross
     */
    private Double getGainLossGross(Double emvGross, Double bmvGross) {
        return BigFunctions.subtract(emvGross, bmvGross);
    }

    /**
     * The method calculates emv gross.
     *
     * @param bmvGross the begin market value
     * @return emv gross
     */
    private Double getEmvGross(Double bmvGross) {
        Random random = new Random();
        boolean boolRandom = random.nextBoolean();

        if (boolRandom) {
            return BigFunctions.add(bmvGross, BigFunctions.divide(bmvGross * random.nextInt(10), 100.0));
        } else {
            return BigFunctions.subtract(bmvGross, BigFunctions.divide(bmvGross * random.nextInt(10), 100.0));
        }
    }

    /**
     * The method distributes total between groups and subgroups.
     *
     * @param securityIdAndAggregateID map of groups and subgroups
     * @param total                    amount that is distributed between groups and subgroups.
     * @return an array with a divided total between groups and subgroups
     */
    private Double[] getDistributionBmv(Map<Integer, Integer> securityIdAndAggregateID, int total) {

        Map<Integer, Integer> countSecurityIdAndAggregateID = getCountSecurityIdAndAggregateID(securityIdAndAggregateID);
        Double[] distributionBmv = new Double[securityIdAndAggregateID.size()];
        int[] totalAggregate = new int[countSecurityIdAndAggregateID.size()];
        Random rand = new Random();

        for (int i = 0; i < totalAggregate.length - 1; i++) {
            totalAggregate[i] = rand.nextInt(total);
            total -= totalAggregate[i];
        }
        totalAggregate[totalAggregate.length - 1] = total;

        for (int i = 0, j = 0; i < totalAggregate.length; i++, j++) {
            for (int k = 0; k < countSecurityIdAndAggregateID.get(i) - 1; k++, j++) {
                distributionBmv[j] = (double) rand.nextInt(totalAggregate[i]);
                totalAggregate[i] -= distributionBmv[j];
            }
            distributionBmv[j] = (double) totalAggregate[i];
        }
        return distributionBmv;
    }


}
