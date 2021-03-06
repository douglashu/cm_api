// Licensed to Cloudera, Inc. under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  Cloudera, Inc. licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.cloudera.api.v7;

import static com.cloudera.api.Parameters.SERVICE_NAME;

import com.cloudera.api.model.ApiCommand;
import com.cloudera.api.v6.ServicesResourceV6;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public interface ServicesResourceV7 extends ServicesResourceV6 {

  /**
   * Prepare and start a service.
   *
   * <p>
   * Perform all the steps needed to prepare the service and start it.
   * </p>
   *
   * <p>
   * Available since API v7.
   * </p>
   *
   * @param serviceName The name of the cluster.
   * @return Information about the submitted command.
   */
  @POST
  @Path("/{serviceName}/commands/firstRun")
  public ApiCommand firstRun(
      @PathParam(SERVICE_NAME) String serviceName);

  /**
   * Create the Sentry Server Database. Only works with embedded postgresql
   * database.
   * <p>
   * This command is to be run whenever a new user and database need to be
   * created in the embedded postgresql database for a Sentry service. This
   * command should usually be followed by a call to sentryCreateDatabaseTables.
   * <p>
   * Available since API v7.
   *
   * @param serviceName
   *          Name of the Sentry service on which to run the command.
   * @return Information about the submitted command
   */
  @POST
  @Consumes()
  @Path("/{serviceName}/commands/sentryCreateDatabase")
  public ApiCommand sentryCreateDatabaseCommand(
      @PathParam(SERVICE_NAME) String serviceName);

  /**
   * Create the Sentry Server Database tables.
   * <p>
   * This command is to be run whenever a new database has been specified. Will
   * do nothing if tables already exist. Will not perform an upgrade. Only
   * Available when Sentry Server is stopped.
   * <p>
   * Available since API v7.
   *
   * @param serviceName Name of the Sentry service on which to run the command.
   * @return Information about the submitted command
   */
  @POST
  @Consumes()
  @Path("/{serviceName}/commands/sentryCreateDatabaseTables")
  public ApiCommand sentryCreateDatabaseTablesCommand(
      @PathParam(SERVICE_NAME) String serviceName);
}
