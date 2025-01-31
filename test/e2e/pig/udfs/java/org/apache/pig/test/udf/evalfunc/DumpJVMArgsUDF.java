/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.pig.test.udf.evalfunc;

import java.io.IOException;
import java.util.List;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;

public class DumpJVMArgsUDF extends EvalFunc<String> {


  @Override
  public String exec(Tuple input) throws IOException {
      // After java9, we can instead use ProcessHandle to achive this
      RuntimeMXBean bean = ManagementFactory.getRuntimeMXBean();
      List<String> jvmArgs = bean.getInputArguments();
      StringBuffer sb = new StringBuffer();
      for( String arg : jvmArgs ) {
          System.err.println(arg);
          sb.append(arg);
          sb.append(" ");
      }
      return sb.toString();
  }
}
