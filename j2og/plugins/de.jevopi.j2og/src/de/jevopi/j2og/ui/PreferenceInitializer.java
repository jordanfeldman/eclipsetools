/*******************************************************************************
 * Copyright (c) 2012 Jens von Pilgrim
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU General Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 *
 * Contributors:
 *    Jens von Pilgrim - initial API and implementation
 ******************************************************************************/
package de.jevopi.j2og.ui;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import de.jevopi.j2og.Plugin;

/**
 * @author Jens von Pilgrim (developer@jevopi.de)
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	public final static String OMNIGRAFFLE_APP = "OMNIGRAFFLE_APP";

	public final static String SHOW_PRIVATE = "SHOW_PRIVATE";
	public final static String SHOW_PACKAGE = "SHOW_PACKAGE";
	public final static String SHOW_PROTECTED = "SHOW_PROTECTED";
	public final static String SHOW_PUBLIC = "SHOW_PUBLIC";

	public final static String SHOW_GETTERSETTER = "SHOW_GETTERSETTER";
	public final static String SHOW_ATTRIBUTTYPES = "SHOW_ATTRIBUTTYPES";
	public final static String SHOW_PARAMETERTYPES = "SHOW_PARAMETERTYPES";
	public final static String SHOW_PARAMETERNAMES = "SHOW_PARAMETERNAMES";

	public final static String SHOW_ATTRIBUTES = "SHOW_ATTRIBUTES";
	public final static String SHOW_OPERATIONS = "SHOW_OPERATIONS";
	public final static String SHOW_OVERRIDINGS = "SHOW_OVERRIDINGS";

	public final static String SHOW_STATICATTRIBUTES = "SHOW_STATICATTRIBUTES";
	public final static String SHOW_STATICOPERATIONS = "SHOW_STATICOPERATIONS";

	public final static String CONVERTATTRIBUTESTOASSOCIATIONS =
		"CONVERTATTRIBUTESTOASSOCIATIONS";
	public final static String FORCEALLASSOCIATIONS = "FORCEALLASSOCIATIONS";
	public final static String SHOW_DEPENDENCIES = "SHOW_DEPENDENCIES";

	public static final String RECURSIVE = "RECURSIVE";

	public static final String OMIT_COMMON_PACKAGEPREFIX = "OMIT_COMMON_PACKAGEPREFIX";

	public static final String SHOW_CONTEXT = "SHOW_CONTEXT";

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = Plugin.getDefault().getPreferenceStore();

		String omniGraffle = findOmniGraffle();

		store.setDefault(OMNIGRAFFLE_APP, omniGraffle);
		store.setDefault(SHOW_PRIVATE, false);
		store.setDefault(SHOW_PRIVATE, false);
		store.setDefault(SHOW_PACKAGE, false);
		store.setDefault(SHOW_PROTECTED, false);
		store.setDefault(SHOW_PUBLIC, true);

		store.setDefault(SHOW_GETTERSETTER, false);
		store.setDefault(SHOW_ATTRIBUTTYPES, true);
		store.setDefault(SHOW_PARAMETERTYPES, true);
		store.setDefault(SHOW_PARAMETERNAMES, false);

		store.setDefault(SHOW_ATTRIBUTES, true);
		store.setDefault(SHOW_OPERATIONS, true);
		store.setDefault(SHOW_OVERRIDINGS, false);

		store.setDefault(SHOW_STATICATTRIBUTES, false);
		store.setDefault(SHOW_STATICOPERATIONS, true);

		store.setDefault(CONVERTATTRIBUTESTOASSOCIATIONS, true);
		store.setDefault(FORCEALLASSOCIATIONS, true);
		store.setDefault(SHOW_DEPENDENCIES, true);
		
		store.setDefault(RECURSIVE, false);
		store.setDefault(OMIT_COMMON_PACKAGEPREFIX, true);
		store.setDefault(SHOW_CONTEXT, false);

	}

	/**
	 * @return
	 * @since Aug 21, 2011
	 */
	public static String findOmniGraffle() {
		File[] searchFolders =
			{
				new File("/Applications"),
				new File(System.getProperty("user.home") + "/Applications")
			};

		List<File> ogs = new ArrayList<File>();
		findOmniGraffle(searchFolders, ogs);
		
		if (ogs.isEmpty()) return "OmniGraffle.app";
		File newest=null;
		for (File f: ogs) {
			if (newest==null || newest.lastModified()<f.lastModified())
				newest = f;
		}
		String s = newest.getName(); 
		return s.substring(0, s.length()-4);
	}

	/**
	 * @param i_searchFolders
	 * @return
	 * @since Aug 21, 2011
	 */
	private static void findOmniGraffle(File[] i_searchFolders,
			List<File> o_Files) {
		for (File f : i_searchFolders) {
			if (f.exists() && f.isDirectory()) {
				for (File apps : f.listFiles(new FileFilter() {

					@Override
					public boolean accept(File i_pathname) {
						return i_pathname.getName().endsWith(".app");
					}
				})) {
					String s = apps.getName().toLowerCase();
					if (s.contains("omnigraffle")) {
						o_Files.add(apps);
					}

				}
				findOmniGraffle(f.listFiles(new FileFilter() {

					@Override
					public boolean accept(File i_pathname) {
						return i_pathname.isDirectory()
							&& !i_pathname.getName().endsWith(".app");
					}
				}), o_Files);
			}
		}
	}

}
