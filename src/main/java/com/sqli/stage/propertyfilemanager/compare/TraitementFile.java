package com.sqli.stage.propertyfilemanager.compare;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class TraitementFile {
	@Value("${propertie.commun.name}")
	String nameFileProperty;

	public TraitementFile() {
		super();

	}
	/*
	 * 
	 * method for scanned file property and ignore spacing and comments .
	 */

	public Map<String, List<String>> scannedFile(MultipartFile file) throws IOException {
		BufferedInputStream bis = new BufferedInputStream(file.getInputStream());
		ZipInputStream zis = new ZipInputStream(bis);
		ZipEntry ze = null;
		Scanner sc;

		Map<String, List<String>> zipZ = new HashMap<String, List<String>>();
		while ((ze = zis.getNextEntry()) != null) {
			sc = new Scanner(zis);
			List<String> params = new ArrayList<String>();

			while (sc.hasNextLine()) {
				String par = sc.nextLine();
				if (!par.equals("") && (!par.matches("#(.*)"))) {
					params.add(par);
				}
			}
			zipZ.put(ze.getName(), params);

		}

		return zipZ;
	}
	/*
	 * 
	 * method split value property files .
	 */

	public Map<String, Map<String, String>> splitListe(Map<String, List<String>> listproperty) {
		Map<String, Map<String, String>> listFinal = new HashMap<String, Map<String, String>>();
		Iterator<Entry<String, List<String>>> iterator = listproperty.entrySet().iterator();
		while (iterator.hasNext()) {

			Entry<String, List<String>> mapentry = iterator.next();
			String nameFileZip = mapentry.getKey();
			List<String> list = mapentry.getValue();
			Map<String, String> listValueFileZip = list.stream().map(s -> s.split("="))
					.collect(Collectors.toMap(s -> s[0], s -> s[1]));
			listFinal.put(nameFileZip, listValueFileZip);
		}
		return listFinal;

	}
	/*
	 * 
	 * 
	 */

	public Map<String, Map<String, String>> addProtertieCommonToSpecifique(Map<String, Map<String, String>> listFile) {
		Map<String, String> communFile = new HashMap<String, String>();
		Map<String, Map<String, String>> specFile = new HashMap<String, Map<String, String>>();

		Iterator<Entry<String, Map<String, String>>> iteratorListFile = listFile.entrySet().iterator();
		while (iteratorListFile.hasNext()) {
			Entry<String, Map<String, String>> mapentry = iteratorListFile.next();
			if (mapentry.getKey().equals(nameFileProperty)) {
				communFile.putAll(mapentry.getValue());
			} else {
				specFile.put(mapentry.getKey(), mapentry.getValue());
			}
		}

		// System.out.println(" commun property : " + communFile);
		// System.out.println(" spec property : " + specFile);

		Iterator<Entry<String, String>> iteratorCommunFile = communFile.entrySet().iterator();
		while (iteratorCommunFile.hasNext()) {

			Entry<String, String> mapFirst = iteratorCommunFile.next();

			Iterator<Entry<String, Map<String, String>>> iterator2 = specFile.entrySet().iterator();
			while (iterator2.hasNext()) {
				Entry<String, Map<String, String>> mapspec = iterator2.next();
				Map<String, String> xx = mapspec.getValue();

				if (!xx.containsKey(mapFirst.getKey())) {

					xx.put(mapFirst.getKey(), mapFirst.getValue());
				}
			}
		}
		/*
		System.out.println("+++++++++++++++++++++++++ Final +++++++++++++++++++");
		System.out.println(" commun property  : " + communFile);
		System.out.println(" spec property  : " + specFile);
*/
		return specFile;
	}
	/*
	 * 
	 * Compare specific files
	 */

	public void compareFileProperties(Map<String, Map<String, String>> listFileProperty) {

		listFileProperty.forEach((key1, value1) -> {

			listFileProperty.forEach((key2, value2) -> {

				if (!key1.equals(key2)) {

					value2.forEach((keyValue2, valueValue2) -> {

						if (value1.containsKey(keyValue2)) {

							if (!value1.get(keyValue2).equals(valueValue2)) {

								System.out.println(" la valure different ' " + valueValue2 + " ' de key " + keyValue2
										+ " dans file" + key2);
							}

						} else {
							System.out.println("le parametre oublie'" + keyValue2 + "' dans " + key1);
						}
					});
				}
			});

		});

	}

}