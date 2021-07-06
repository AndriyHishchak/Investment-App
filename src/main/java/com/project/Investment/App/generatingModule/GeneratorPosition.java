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

@Component
@Slf4j
public class GeneratorPosition {

    public List<Position> generatorPosition(LocalDate startDate, String entityId, int total) {
        List<Position> positions = new ArrayList<>();
        Map<Integer, Integer> securityIdAndAggregateID = generatorSecurityIdAndAggregateID();
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

            if (date.getDayOfWeek().getValue() < 6) {

                int firstPosition = positions.size() - securityIdAndAggregateID.size();
                Double yesterdayTotal = getYesterdayTotal(positions, firstPosition);

                for (int i = 0, securityId = 1, k = firstPosition; i < securityIdAndAggregateID.size(); i++, securityId++, k++) {

                    Double bmvGross = positions.get(k).getEmvGross();
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

    public Map<Integer, Integer> generatorSecurityIdAndAggregateID() {
        Map<Integer, Integer> securityIdAndAggregateID = new HashMap<>();
        securityIdAndAggregateID.put(1, 3);
        securityIdAndAggregateID.put(2, 3);
        securityIdAndAggregateID.put(3, 3);
        securityIdAndAggregateID.put(4, 3);
        securityIdAndAggregateID.put(5, 3);
        securityIdAndAggregateID.put(6, 3);
        securityIdAndAggregateID.put(7, 3);
        securityIdAndAggregateID.put(8, 3);
        securityIdAndAggregateID.put(9, 3);
        securityIdAndAggregateID.put(10, 3);
        securityIdAndAggregateID.put(11, 4);
        securityIdAndAggregateID.put(12, 4);
        securityIdAndAggregateID.put(13, 4);
        securityIdAndAggregateID.put(14, 4);
        securityIdAndAggregateID.put(15, 4);
        securityIdAndAggregateID.put(16, 4);
        securityIdAndAggregateID.put(17, 4);
        securityIdAndAggregateID.put(18, 4);
        securityIdAndAggregateID.put(19, 4);
        securityIdAndAggregateID.put(20, 4);
        securityIdAndAggregateID.put(21, 5);
        securityIdAndAggregateID.put(22, 5);
        securityIdAndAggregateID.put(23, 5);
        securityIdAndAggregateID.put(24, 5);
        securityIdAndAggregateID.put(25, 5);
        securityIdAndAggregateID.put(26, 5);
        securityIdAndAggregateID.put(27, 5);
        securityIdAndAggregateID.put(28, 5);
        securityIdAndAggregateID.put(29, 5);
        securityIdAndAggregateID.put(30, 5);
        securityIdAndAggregateID.put(31, 6);
        securityIdAndAggregateID.put(32, 6);
        securityIdAndAggregateID.put(33, 6);
        securityIdAndAggregateID.put(34, 6);
        securityIdAndAggregateID.put(35, 6);
        securityIdAndAggregateID.put(36, 6);
        securityIdAndAggregateID.put(37, 6);
        securityIdAndAggregateID.put(38, 6);
        securityIdAndAggregateID.put(39, 6);
        securityIdAndAggregateID.put(40, 6);

        return securityIdAndAggregateID;
    }

    private Double getYesterdayTotal(List<Position> positions, Integer firstSecurity) {
        return positions.stream().skip(firstSecurity).map(Position::getEmvGross).reduce(Double::sum).get();
    }

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
        return newSecurityIdAndAggregateID;
    }

    private Double getReturnGross(Double bmvGross, Double emvGross) {
        if (BigFunctions.equals(emvGross, 0.0)) {
            return 0.0;
        }
        return BigFunctions.multiply(BigFunctions.divide(BigFunctions.subtract(emvGross, bmvGross), bmvGross), 100.0);
    }

    private Double getWeight(Double bmvGross, Double total) {
        return BigFunctions.multiply(BigFunctions.divide(bmvGross, total), 100.0);
    }

    private Double getGainLossGross(Double emvGross, Double bmvGross) {
        return BigFunctions.subtract(emvGross, bmvGross);
    }

    private Double getEmvGross(Double bmvGross) {
        Random random = new Random();
        boolean boolRandom = random.nextBoolean();

        if (boolRandom) {
            return BigFunctions.add(bmvGross, BigFunctions.divide(bmvGross * random.nextInt(10), 100.0));
        } else {
            return BigFunctions.subtract(bmvGross, BigFunctions.divide(bmvGross * random.nextInt(10), 100.0));
        }
    }

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
