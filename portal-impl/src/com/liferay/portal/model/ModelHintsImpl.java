/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.model;

import com.liferay.portal.kernel.security.pacl.DoPrivileged;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.util.PropsUtil;

/**
 * @author Raymond Augé
 */
@DoPrivileged
public class ModelHintsImpl extends BaseModelHintsImpl {

	@Override
	public ModelHintsCallback getModelHintsCallback() {
		return _modelHintsCallback;
	}

	@Override
	public String[] getModelHintsConfigs() {
		return _modelHintsConfigs;
	}

	public class RuntimeModelHintsCallback implements ModelHintsCallback {

		@Override
		public void execute(ClassLoader classLoader, String name) {
			if (classLoader != BaseModelHintsImpl.class.getClassLoader()) {
				ClassNameLocalServiceUtil.getClassName(name);
			}
		}

	}

	private final ModelHintsCallback _modelHintsCallback =
		new RuntimeModelHintsCallback();
	private final String[] _modelHintsConfigs = StringUtil.split(
		PropsUtil.get(PropsKeys.MODEL_HINTS_CONFIGS));

}