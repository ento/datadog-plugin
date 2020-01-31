package org.datadog.jenkins.plugins.datadog.stubs;

import hudson.EnvVars;
import hudson.model.*;
import org.jenkinsci.plugins.workflow.job.WorkflowJob;
import org.jenkinsci.plugins.workflow.job.WorkflowRun;

import java.io.IOException;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WorkflowRunStub {

    public static WorkflowRun createRun(WorkflowJob job, Result result, EnvVars envVars, WorkflowRun previousSuccessfulBuild,
                     long duration, int number, WorkflowRun previousBuiltBuild, long timestamp, WorkflowRun previousNotFailedBuild) throws IOException, InterruptedException {
        // Use mocking instead of subclassing like BuildStub does, as WorkflowRun
        // is a final class.
        WorkflowRun run = mock(WorkflowRun.class);
        when(run.getParent()).thenReturn(job);
        when(run.getResult()).thenReturn(result);
        when(run.getEnvironment(any(TaskListener.class))).thenReturn(envVars);
        when(run.getPreviousSuccessfulBuild()).thenReturn(previousSuccessfulBuild);
        when(run.getDuration()).thenReturn(duration);
        when(run.getNumber()).thenReturn(number);
        when(run.getPreviousBuiltBuild()).thenReturn(previousBuiltBuild);
        when(run.getStartTimeInMillis()).thenReturn(timestamp);
        when(run.getPreviousNotFailedBuild()).thenReturn(previousNotFailedBuild);
        when(run.getQueueId()).thenReturn(1L);
        return run;
    }
}
