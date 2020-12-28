
/*
 * Copyright 2020 Lenses.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.lenses.streamreactor.connect.aws.s3

import org.slf4j.Logger
import org.slf4j.LoggerFactory

trait MetricsSupport {
  private val logger: Logger = LoggerFactory getLogger getClass.getName
  // Do not log actions taking 0 msec or 1 msec
  val threasholdMsec = 1

  def timed[T](msg: String)(thunk: => T): T = {
    val t0 = System.nanoTime()
    val result = thunk // call-by-name
    val duration = (System.nanoTime() - t0) / 1000000
    if (duration > threasholdMsec) {
      logger.info(s"Timed($msg): " + duration + " msec")
    }
    result
  }
}
