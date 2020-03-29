/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.hasor.dataql.fx.web;
import net.hasor.dataql.UdfSourceAssembly;
import net.hasor.utils.StringUtils;

import javax.inject.Singleton;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Header 函数库。函数库引入 <code>import 'net.hasor.dataql.fx.web.HeaderUdfSource' as headerTools;</code>
 * @author 赵永春 (zyc@hasor.net)
 * @version : 2020-03-29
 */
@Singleton
public class HeaderUdfSource implements UdfSourceAssembly {
    /** headerMap */
    public static Map<String, String> map() {
        return InvokerInterceptor.headerMap();
    }

    /** headerMap,Value是数组 */
    public static Map<String, List<String>> arrayMap() {
        return InvokerInterceptor.headerArrayMap();
    }

    /** 添加 Header */
    public static boolean add(String headerName, String value) {
        if (StringUtils.isBlank(headerName)) {
            return false;
        }
        InvokerInterceptor.invoker().getHttpResponse().addHeader(headerName, value);
        return true;
    }

    /** 设置 Header */
    public static boolean set(String headerName, String value) {
        if (StringUtils.isBlank(headerName)) {
            return false;
        }
        InvokerInterceptor.invoker().getHttpResponse().setHeader(headerName, value);
        return true;
    }

    /** 设置 HeaderMap */
    public static boolean addAll(Map<String, String> headerMap) {
        if (headerMap != null) {
            HttpServletResponse httpResponse = InvokerInterceptor.invoker().getHttpResponse();
            headerMap.forEach(httpResponse::setHeader);
            return true;
        }
        return false;
    }
}