package com.project.Investment.App.dto.validator.groupSequence;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence(value = {Default.class, First.class, Second.class})
public interface OrderedChecks {}
