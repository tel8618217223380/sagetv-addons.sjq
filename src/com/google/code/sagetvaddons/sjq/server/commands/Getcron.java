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

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.commons.io.FileUtils;

import com.google.code.sagetvaddons.sjq.listener.Command;

/**
 * <p>Provides the ability to read the contents of the server's crontab file
 * <p><pre>
 *    W: String (crontab contents)
 * </pre></p>
 * @author dbattams
 * @version $Id$
 */
public final class Getcron extends Command {
	static public final File CRONTAB_FILE = new File("plugins/sjq/crontab");

	/**
	 * @param in
	 * @param out
	 */
	public Getcron(ObjectInputStream in, ObjectOutputStream out) {
		super(in, out);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.google.code.sagetvaddons.sjq.listener.Command#execute()
	 */
	@Override
	public void execute() throws IOException {
		String crontab;
		if(!CRONTAB_FILE.exists())
			crontab = "";
		else
			crontab = FileUtils.readFileToString(CRONTAB_FILE, "UTF-8");
		getOut().writeUTF(crontab);
		getOut().flush();
	}
}
