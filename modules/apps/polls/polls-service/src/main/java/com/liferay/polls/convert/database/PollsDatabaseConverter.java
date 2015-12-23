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

package com.liferay.polls.convert.database;

import com.liferay.portal.convert.database.DatabaseConverter;
import com.liferay.portal.convert.util.HibernateModelLoaderUtil;
import com.liferay.portal.convert.util.ModelMigrator;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Cristina González
 */
@Component(immediate = true, service = DatabaseConverter.class)
public class PollsDatabaseConverter implements DatabaseConverter {

	@Override
	public void convert(DataSource dataSource) throws Exception {
		Class<?> clazz = getClass();

		_modelMigrator.migrate(
			dataSource,
			HibernateModelLoaderUtil.getModelClassNames(
				clazz.getClassLoader(), ".*Polls.*"));
	}

	@Reference(unbind = "-")
	private void setModelMigrator(ModelMigrator modelMigrator) {
		_modelMigrator = modelMigrator;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PollsDatabaseConverter.class);

	private ModelMigrator _modelMigrator;

}