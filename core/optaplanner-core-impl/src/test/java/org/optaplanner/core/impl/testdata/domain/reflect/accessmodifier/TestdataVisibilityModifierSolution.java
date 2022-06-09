package org.optaplanner.core.impl.testdata.domain.reflect.accessmodifier;

import java.util.List;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.solution.ProblemFactProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.simple.SimpleScore;
import org.optaplanner.core.impl.domain.solution.descriptor.SolutionDescriptor;
import org.optaplanner.core.impl.testdata.domain.TestdataEntity;
import org.optaplanner.core.impl.testdata.domain.TestdataObject;
import org.optaplanner.core.impl.testdata.domain.TestdataValue;

@PlanningSolution
public class TestdataVisibilityModifierSolution extends TestdataObject {

    public static SolutionDescriptor<TestdataVisibilityModifierSolution> buildSolutionDescriptor() {
        return SolutionDescriptor.buildSolutionDescriptor(TestdataVisibilityModifierSolution.class, TestdataEntity.class);
    }

    private String privateField;
    public String publicField;
    private String privatePropertyField;
    private String friendlyPropertyField;
    private String protectedPropertyField;
    private String publicPropertyField;

    private List<TestdataValue> valueList;
    private List<TestdataEntity> entityList;

    private SimpleScore score;

    private TestdataVisibilityModifierSolution() {
    }

    public TestdataVisibilityModifierSolution(String code) {
        super(code);
    }

    public TestdataVisibilityModifierSolution(String code, String privateField, String publicField,
            String privateProperty, String friendlyProperty, String protectedProperty, String publicProperty) {
        super(code);
        this.privateField = privateField;
        this.publicField = publicField;
        this.privatePropertyField = privateProperty;
        this.friendlyPropertyField = friendlyProperty;
        this.protectedPropertyField = protectedProperty;
        this.publicPropertyField = publicProperty;
    }

    @ProblemFactProperty
    private String getPrivateProperty() {
        return privatePropertyField;
    }

    private void setPrivateProperty(String privateProperty) {
        this.privatePropertyField = privateProperty;
    }

    @ProblemFactProperty
    String getFriendlyProperty() {
        return friendlyPropertyField;
    }

    void setFriendlyProperty(String friendlyProperty) {
        this.friendlyPropertyField = friendlyProperty;
    }

    @ProblemFactProperty
    protected String getProtectedProperty() {
        return protectedPropertyField;
    }

    protected void setProtectedProperty(String protectedProperty) {
        this.protectedPropertyField = protectedProperty;
    }

    @ProblemFactProperty
    public String getPublicProperty() {
        return publicPropertyField;
    }

    public void setPublicProperty(String publicProperty) {
        this.publicPropertyField = publicProperty;
    }

    @ValueRangeProvider(id = "valueRange")
    @ProblemFactCollectionProperty
    public List<TestdataValue> getValueList() {
        return valueList;
    }

    public void setValueList(List<TestdataValue> valueList) {
        this.valueList = valueList;
    }

    @PlanningEntityCollectionProperty
    public List<TestdataEntity> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<TestdataEntity> entityList) {
        this.entityList = entityList;
    }

    @PlanningScore
    public SimpleScore getScore() {
        return score;
    }

    public void setScore(SimpleScore score) {
        this.score = score;
    }

    // ************************************************************************
    // Complex methods
    // ************************************************************************

}
