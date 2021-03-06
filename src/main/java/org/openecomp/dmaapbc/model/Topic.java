/*-
 * ============LICENSE_START=======================================================
 * OpenECOMP - org.openecomp.dmaapbc
 * ================================================================================
 * Copyright (C) 2017 AT&T Intellectual Property. All rights reserved.
 * ================================================================================
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
 * ============LICENSE_END=========================================================
 */

package org.openecomp.dmaapbc.model;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.openecomp.dmaapbc.service.DmaapService;


@XmlRootElement
public class Topic extends DmaapObject  {

	private String fqtn;
	private	String topicName;
	private	String	topicDescription;
	private	String	tnxEnabled;
	private	String	owner;
	private String	formatUuid;
	private ReplicationType	replicationCase;  
	private String	globalMrURL;		// optional: URL of global MR to replicate to/from

	private	ArrayList<MR_Client> clients;


	
	private static Dmaap dmaap = new DmaapService().getDmaap();
	
	//
	// utility function to generate the FQTN of a topic
	public static String genFqtn(  String name ) {
		CharSequence signal = ".";
		String ret;
		if ( name.contains( signal )) {
			// presence of a dot indicates the name is already fully qualified
			ret = name;
		} else {
			ret = dmaap.getTopicNsRoot() + "." + dmaap.getDmaapName() + "." + name;
		}
		return ret;
	}



	public Topic() {
		super();
		this.clients = new ArrayList<MR_Client>();
		this.lastMod = new Date();
		this.replicationCase = ReplicationType.Validator("none");
		this.setLastMod();
		logger.debug( "Topic constructor " + this.lastMod );
	}
	public Topic(String fqtn, String topicName, String topicDescription,
			 String tnxEnabled, String owner) {
		super();
		this.fqtn = fqtn;
		this.topicName = topicName;
		this.topicDescription = topicDescription;
		//this.dcaeLocationName = dcaeLocationName;
		this.tnxEnabled = tnxEnabled;
		this.owner = owner;
		this.setLastMod();
		this.setStatus( DmaapObject_Status.NEW );
		this.replicationCase = ReplicationType.Validator("none");
		logger.debug( "Topic constructor " + this.getLastMod() );
	}
	public String getFqtn() {
		return fqtn;
	}
	public void setFqtn(String fqtn) {
		this.fqtn = fqtn;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public String getTopicDescription() {
		return topicDescription;
	}
	public void setTopicDescription(String topicDescription) {
		this.topicDescription = topicDescription;
	}

	public String getTnxEnabled() {
		return tnxEnabled;
	}
	public void setTnxEnabled(String tnxEnabled) {
		this.tnxEnabled = tnxEnabled;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}


	public void setClients(ArrayList<MR_Client> clients) {
		this.clients = clients;
	}

	public ArrayList<MR_Client> getClients() {
		return clients;
	}

	public int getNumClients() {
		if ( this.clients == null ) {
			return 0;
		}
		return this.clients.size();
	}




	public String getFormatUuid() {
		return formatUuid;
	}



	public void setFormatUuid(String formatUuid) {
		this.formatUuid = formatUuid;
	}


	public ReplicationType getReplicationCase() {
		return replicationCase;
	}



	/*
	public void setReplicationCase(String val) {
		this.replicationCase = ReplicationType.Validator(val);
	}
	*/
	
	public void setReplicationCase(ReplicationType t) {
		this.replicationCase = t;
	}


	public String getGlobalMrURL() {
		return globalMrURL;
	}



	public void setGlobalMrURL(String globalMrURL) {
		this.globalMrURL = globalMrURL;
	}



	public String toProvJSON() {
		StringBuilder str = new StringBuilder();
		str.append("{ \"topicName\": \"");
		str.append( this.getFqtn() );
		str.append( "\", \"topicDescription\": \"");
		str.append( this.getTopicDescription());
		str.append( "\", \"partitionCount\": \"2\", \"replicationCount\": \"1\" } ");
		logger.info( str.toString() );
		return str.toString();
	}
	
	public byte[] getBytes() {
		return toProvJSON().getBytes(StandardCharsets.UTF_8);
	}
}
