/*
 * Copyright 2016. Burke Choi All rights reserved.
 *             http://www.sarangnamu.net
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

package net.sarangnamu.common;

/**
 * Created by <a href="mailto:aucd29@gmail.com">Burke Choi</a> on 2016. 3. 22.. <p/>
 */
public abstract class InflateFrgmtBase extends FrgmtBase {
    private static final org.slf4j.Logger mLog = org.slf4j.LoggerFactory.getLogger(InflateFrgmtBase.class);

    protected static final String PREFIX_PAGE         = "view_";
    protected static final String SUFFIX_FRAGMENT     = "Frgmt";
    protected static final String IDENTIFIER_LAYOUT   = "layout";

    protected String mParseClassName;

    @Override
    protected int getLayoutId() {
        String layoutFileName = getPrefixForPage() + getClassSimpleName();

        if (mLog.isDebugEnabled()) {
            mLog.debug("load : " + layoutFileName);
        }

        return getResources().getIdentifier(layoutFileName, IDENTIFIER_LAYOUT,
                getActivity().getPackageName());
    }

    protected String getClassSimpleName() {
        if (mParseClassName != null && mParseClassName.length() > 0) {
            return mParseClassName;
        }

        StringBuilder sb = new StringBuilder();
        String tmpName = getClass().getSimpleName().replace(getSuffixForFragment(), "");
        sb.append(Character.toLowerCase(tmpName.charAt(0)));
        for (int i = 1; i < tmpName.length(); ++i) {
            char c = tmpName.charAt(i);

            if (Character.isUpperCase(c)) {
                sb.append('_');
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }

        mParseClassName = sb.toString();

        return mParseClassName;
    }

    @Override
    protected String getDefalutViewMessage() {
        return super.getDefalutViewMessage() + "\n(File not found '" + getClassSimpleName() + ".xml')";
    }

    protected String getPrefixForPage() {
        return PREFIX_PAGE;
    }

    protected String getSuffixForFragment() {
        return SUFFIX_FRAGMENT;
    }
}
