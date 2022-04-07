/**
 *    Copyright 2009-2015 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.session;

/**
 * @author Clinton Begin
 * @see org.apache.ibatis.plugin.InterceptorChain#pluginAll(Object)
 * 可被拦截对象之一
 * @see org.apache.ibatis.executor.Executor
 * @see org.apache.ibatis.executor.statement.StatementHandler
 * @see org.apache.ibatis.executor.parameter.ParameterHandler
 * --
 * @see org.apache.ibatis.plugin.Intercepts
 * @see org.apache.ibatis.plugin.Signature
 */
public interface ResultHandler<T> {

  void handleResult(ResultContext<? extends T> resultContext);

}
