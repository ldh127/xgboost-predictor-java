/*
 * Copyright 2016 LinkedIn Corp. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.linkedin.photon.build.plugins

/**
 * This class provides utils to resolve Scala version string into the suffix string
 * used for cross-build purpose.
 *  The resolution rule is:
 *   'x.y.z' -->  '_x.y'   (if x == 2 and y >= 10)
 *                '_x.y.z' (otherwise)
 */
class ScalaUtils {
  static def getScalaVersionSuffix(scalaVersion) {
    def tokens = scalaVersion.split('\\.')
    if (tokens == null || tokens.length < 3) {
      throw new RuntimeException ("Illegal scalaVersion string: [$scalaVersion]. " +
          "A valid version string should in the format of [x].[y].[z] .");
    }

    if (tokens[0] == '2' && Integer.parseInt(tokens[1]) >= 10) {
      return "_${tokens[0]}.${tokens[1]}"
    } else {
      return "_$scalaVersion"
    }
  }
}