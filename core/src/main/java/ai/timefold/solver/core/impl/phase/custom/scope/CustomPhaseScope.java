package ai.timefold.solver.core.impl.phase.custom.scope;

import ai.timefold.solver.core.api.domain.solution.PlanningSolution;
import ai.timefold.solver.core.impl.phase.scope.AbstractPhaseScope;
import ai.timefold.solver.core.impl.solver.scope.SolverScope;

/**
 * @param <Solution_> the solution type, the class with the {@link PlanningSolution} annotation
 */
public class CustomPhaseScope<Solution_> extends AbstractPhaseScope<Solution_> {

    private CustomStepScope<Solution_> lastCompletedStepScope;

    public CustomPhaseScope(SolverScope<Solution_> solverScope) {
        this(solverScope, false);
    }

    public CustomPhaseScope(SolverScope<Solution_> solverScope, boolean phaseSendsBestSolutionEvents) {
        super(solverScope, phaseSendsBestSolutionEvents);
        lastCompletedStepScope = new CustomStepScope<>(this, -1);
    }

    @Override
    public CustomStepScope<Solution_> getLastCompletedStepScope() {
        return lastCompletedStepScope;
    }

    public void setLastCompletedStepScope(CustomStepScope<Solution_> lastCompletedStepScope) {
        this.lastCompletedStepScope = lastCompletedStepScope;
    }

    // ************************************************************************
    // Calculated methods
    // ************************************************************************

}
