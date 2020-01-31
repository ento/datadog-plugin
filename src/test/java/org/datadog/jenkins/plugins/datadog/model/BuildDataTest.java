/*
The MIT License

Copyright (c) 2015-Present Datadog, Inc <opensource@datadoghq.com>
All rights reserved.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
 */

package org.datadog.jenkins.plugins.datadog.model;

import hudson.model.*;
import jenkins.model.Jenkins;
import org.datadog.jenkins.plugins.datadog.DatadogUtilities;
import org.datadog.jenkins.plugins.datadog.model.BuildData;
import org.datadog.jenkins.plugins.datadog.stubs.BuildStub;
import org.datadog.jenkins.plugins.datadog.stubs.ProjectStub;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.time.Clock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BuildDataTest {

    @Test
    public void testWithNothingSet() throws IOException, InterruptedException {
        Jenkins jenkins = mock(Jenkins.class);
        when(jenkins.getFullName()).thenReturn(null);

        Clock clock = mock(Clock.class);
        when(clock.millis()).thenReturn(0L);

        ProjectStub job = new ProjectStub(jenkins,null);
        Run run = new BuildStub(job, null, null, null, 0L, 0, null, 0L, null);

        TaskListener listener = mock(TaskListener.class);
        BuildData bd = new BuildData(run, listener);

        Assert.assertTrue(bd.getTags().size() == 1);
        Assert.assertTrue(bd.getTags().get("job").contains("unknown"));
        Assert.assertTrue(bd.getJobName("unknown").equals("unknown"));
        Assert.assertTrue(bd.getResult("unknown").equals("unknown"));
        String hostname = DatadogUtilities.getHostname(null);
        Assert.assertTrue(bd.getHostname("unknown").equals(hostname));
        Assert.assertTrue(bd.getBuildUrl("unknown").equals("unknown"));
        Assert.assertTrue(bd.getNodeName("unknown").equals("unknown"));
        Assert.assertTrue(bd.getBranch("unknown").equals("unknown"));
        Assert.assertTrue(bd.getBuildNumber("unknown").equals("0"));
        Assert.assertTrue(bd.getDuration(-1L).equals(0L));
        Assert.assertTrue(bd.getEndTime(-1L).equals(-1L));
        Assert.assertTrue(bd.getStartTime(-1L).equals(0L));
        Assert.assertTrue(bd.getBuildId("unknown").equals("unknown"));
        Assert.assertTrue(bd.getBuildTag("unknown").equals("unknown"));
        Assert.assertTrue(bd.getJenkinsUrl("unknown").equals("unknown"));
        Assert.assertTrue(bd.getExecutorNumber("unknown").equals("unknown"));
        Assert.assertTrue(bd.getJavaHome("unknown").equals("unknown"));
        Assert.assertTrue(bd.getWorkspace("unknown").equals("unknown"));
        Assert.assertTrue(bd.getGitUrl("unknown").equals("unknown"));
        Assert.assertTrue(bd.getGitCommit("unknown").equals("unknown"));
        Assert.assertTrue(bd.getPromotedUrl("unknown").equals("unknown"));
        Assert.assertTrue(bd.getPromotedJobName("unknown").equals("unknown"));
        Assert.assertTrue(bd.getPromotedNumber("unknown").equals("unknown"));
        Assert.assertTrue(bd.getPromotedId("unknown").equals("unknown"));
        Assert.assertTrue(bd.getPromotedTimestamp("unknown").equals("unknown"));
        Assert.assertTrue(bd.getPromotedUserName("unknown").equals("unknown"));
        Assert.assertTrue(bd.getPromotedUserId("unknown").equals("unknown"));
        Assert.assertTrue(bd.getPromotedJobFullName("unknown").equals("unknown"));
        Assert.assertTrue(bd.getUserId().equals("anonymous"));
    }
}
