---
-- ============LICENSE_START=======================================================
-- OpenECOMP - org.openecomp.dmaapbc
-- ================================================================================
-- Copyright (C) 2017 AT&T Intellectual Property. All rights reserved.
-- ================================================================================
-- Licensed under the Apache License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
-- 
--      http://www.apache.org/licenses/LICENSE-2.0
-- 
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
-- ============LICENSE_END=========================================================
---


@drop table mirror_maker;
create table mirror_maker	(
	mm_name	VARCHAR(512),
	source_cluster	VARCHAR(256),
	target_cluster	VARCHAR(256),
	last_mod	TIMESTAMP,
	topics		TEXT,
	PRIMARY KEY(mm_name)
);


update dmaapbc_sch_ver set version = 6 where version = 5;
