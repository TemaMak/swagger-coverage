package com.github.viclovsky.swagger.coverage.branch.rule.parameter;

import com.github.viclovsky.swagger.coverage.branch.generator.SwaggerSpecificationProccessor;
import com.github.viclovsky.swagger.coverage.branch.model.Branch;
import com.github.viclovsky.swagger.coverage.branch.model.SinglePredicateBranch;
import com.github.viclovsky.swagger.coverage.branch.predicate.BranchPredicate;
import com.github.viclovsky.swagger.coverage.branch.predicate.NotOnlyParameterListValueBranchPredicate;
import io.swagger.models.parameters.Parameter;

import java.util.List;

public class NotOnlyEnumValuesBranchRule extends ParameterRule {


    @Override
    public Branch processParameter(Parameter parameter) {
        List<String> enumValues = SwaggerSpecificationProccessor.extractEnum(parameter);

        if (enumValues != null && !enumValues.isEmpty()) {


            BranchPredicate predicate = new NotOnlyParameterListValueBranchPredicate(parameter.getName(),enumValues);
            Branch branch = new SinglePredicateBranch(
                    parameter.getIn() + " {" + parameter.getName() + "} contains values not only from enum " + enumValues,
                    "",
                    predicate
            );

            return branch;
        }

        return null;
    }

    @Override
    public String getId() {
        return null;
    }
}