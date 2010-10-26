/*
 *      Copyright 2010 Battams, Derek
 *       
 *       Licensed under the Apache License, Version 2.0 (the "License");
 *       you may not use this file except in compliance with the License.
 *       You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *       Unless required by applicable law or agreed to in writing, software
 *       distributed under the License is distributed on an "AS IS" BASIS,
 *       WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *       See the License for the specific language governing permissions and
 *       limitations under the License.
 */
package com.google.code.sagetvaddons.sjq.server.commands;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.google.code.sagetvaddons.sjq.listener.Command;
import com.google.code.sagetvaddons.sjq.listener.NetworkAck;
import com.google.code.sagetvaddons.sjq.server.TaskQueue;
import com.google.code.sagetvaddons.sjq.shared.Client;

/**
 * <p>Provides the ability to delete an idle task client from the list of registered task clients</p>
 * <p><pre>
 *    R: Client
 *    W: ACK
 * </pre></p>
 * @author dbattams
 * @version $Id$
 */
public final class Rmclnt extends Command {

	/**
	 * @param in
	 * @param out
	 */
	public Rmclnt(ObjectInputStream in, ObjectOutputStream out) {
		super(in, out);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.google.code.sagetvaddons.sjq.listener.Command#execute()
	 */
	@Override
	public void execute() throws IOException {
		Client clnt;
		try {
			clnt = (Client)getIn().readObject();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		getOut().writeObject(NetworkAck.get(TaskQueue.get().deleteClient(clnt) ? NetworkAck.OK : NetworkAck.ERR));
		getOut().flush();
	}

}
