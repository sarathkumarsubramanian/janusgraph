// Copyright 2017 JanusGraph Authors
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package org.janusgraph.blueprints.process;

import org.janusgraph.HBaseStorageSetup;
import org.janusgraph.blueprints.HBaseGraphProvider;
import org.janusgraph.core.JanusGraph;
import org.apache.hadoop.hbase.util.VersionInfo;
import org.apache.tinkerpop.gremlin.GraphProviderClass;
import org.apache.tinkerpop.gremlin.process.ProcessStandardSuite;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;

import java.io.IOException;

/**
 * @author Matthias Broecheler (me@matthiasb.com)
 */
@RunWith(ProcessStandardSuite.class)
@GraphProviderClass(provider = HBaseGraphProvider.class, graph = JanusGraph.class)
public class HBaseProcessTest {

    @BeforeAll
    public static void startHBase() {
        try {
            HBaseStorageSetup.startHBase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterAll
    public static void stopHBase() {
        // Workaround for https://issues.apache.org/jira/browse/HBASE-10312
        if (VersionInfo.getVersion().startsWith("0.96"))
            HBaseStorageSetup.killIfRunning();
    }
}
