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

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class TraitementFile {

	public TraitementFile() {
		super();

	}

	public Map<String, List<String>> scannedFile(MultipartFile file) throws IOException {
		BufferedInputStream bis = new BufferedInputStream(file.getInputStream());
		ZipInputStream zis = new ZipInputStream(bis);
		ZipEntry ze = null;
		Map<String, List<String>> zipZ = new HashMap<String, List<String>>();
		while ((ze = zis.getNextEntry()) != null) {
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(zis);
			List<String> params = new ArrayList<String>();
			while (sc.hasNextLine()) {
				String par = sc.nextLine();
				if ((par != "") & (!par.matches("#(.*)"))) {
					params.add(par);
				}

			}
			zipZ.put(ze.getName(), params);
		}
		return zipZ;
	}

	public Map<String, Map<String, String>> splitListe(Map<String, List<String>> listproperty) {
		Map<String, Map<String, String>> listFinal = new HashMap<String, Map<String, String>>();
		for (Iterator<Entry<String, List<String>>> iterator = listproperty.entrySet().iterator(); iterator.hasNext();) {
			Entry<String, List<String>> mapentry = iterator.next();
			String name = (String) mapentry.getKey();
			List<String> list = (List<String>) mapentry.getValue();
			Map<String, String> Listzip = list.stream().map(s -> s.split("="))
					.collect(Collectors.toMap(s -> s[0], s -> s[1]));
			listFinal.put(name, Listzip);
		}
		return listFinal;
		/*
		 * 
		 * Map<String, Map<String, String>> listFinal = new HashMap<String, Map<String,
		 * String>>(); for (Map.Entry mapentry : listproperty.entrySet()) {
		 * 
		 * String name = (String) mapentry.getKey(); List<String> list = (List<String>)
		 * mapentry.getValue(); Map<String, String> Listzip = list.stream().map(s ->
		 * s.split("=")) .collect(Collectors.toMap(s -> s[0], s -> s[1]));
		 * listFinal.put(name, Listzip); } return listFinal ;
		 */

	}

	public List<String> scannerListe(Map<String, ZipInputStream> mapZis, String namefile) {
		/*
		 * List<String> params = new ArrayList<String>();
		 * 
		 * try (Scanner sc = new Scanner(mapZis.get(namefile))) {
		 * 
		 * while (sc.hasNextLine()) { String par = sc.nextLine();
		 * 
		 * if ((par != "") & (!par.matches("#(.*)"))) { System.out.println(par);
		 * 
		 * params.add(par);
		 * 
		 * }
		 * 
		 * } }
		 */
		return null;
	}
}