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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.sqli.stage.propertyfilemanager.entities.File;
import com.sqli.stage.propertyfilemanager.entities.Propertie;

@Component
public class TraitementFile {

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
				if ((par != "") & (!par.matches("#(.*)"))) {
					params.add(par);
				}
			}
			zipZ.put(ze.getName(), params);

		}

		return zipZ;
	}
	/*
	 * 
	 * method for split value property file .
	 */

	public Map<String, Map<String, String>> splitListe(Map<String, List<String>> listproperty) {
		Map<String, Map<String, String>> listFinal = new HashMap<String, Map<String, String>>();
		Iterator<Entry<String, List<String>>> iterator = listproperty.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, List<String>> mapentry = iterator.next();
			String name = mapentry.getKey();
			List<String> list = mapentry.getValue();
			Map<String, String> Listzip = list.stream().map(s -> s.split("="))
					.collect(Collectors.toMap(s -> s[0], s -> s[1]));
			listFinal.put(name, Listzip);
		}
		return listFinal;

	}

	public Map<String, Map<String, String>> addProtertieCommonToSpecifique(Map<String, Map<String, String>> listFile) {
		Map<String, String> communFile = new HashMap<String, String>();
		Map<String, Map<String, String>> specFile = new HashMap<String, Map<String, String>>();
		String name = "commun.properties";

		Iterator<Entry<String, Map<String, String>>> iteratorListFile = listFile.entrySet().iterator();
		while (iteratorListFile.hasNext()) {
			Entry<String, Map<String, String>> mapentry = iteratorListFile.next();
			if (mapentry.getKey().equals(name)) {
				communFile.putAll(mapentry.getValue());
			} else {
				specFile.put(mapentry.getKey().toString(), mapentry.getValue());
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
					// System.out.println(" Le clé "+ mapFirst.getKey()+" n'existe pas dans " +
					// mapspec.getKey());
					xx.put(mapFirst.getKey().toString(), mapFirst.getValue().toString());
				}
			}
		}

		/*
		 * System.out.println("+++++++++++++++++++++++++ Final +++++++++++++++++++");
		 * System.out.println(" commun property  : " + communFile);
		 * System.out.println(" spec property  : " + specFile);
		 * 
		 */

		return specFile;
	}

	public void CompareFileProperties(Map<String, Map<String, String>> listFileProperty) {
		System.out.println("g" + listFileProperty);
		Entry<String, Map<String, String>> spec1 = null;
		Entry<String, Map<String, String>> spec2 = null;
		//listFileProperty.forEach((v,k)-> {k.forEach((y,z)->{ z.compareTo(z.)});
		
		Iterator<Entry<String, Map<String, String>>> iteratorfile = listFileProperty.entrySet().iterator();
		while (iteratorfile.hasNext()) {
			spec2 = iteratorfile.next();
			spec1 = iteratorfile.next();

			System.out.println("spec1 " + spec1);
			System.out.println("spec2 " + spec2);

			break;
		}
		Map<String, String> specValue1 = spec1.getValue();
		Map<String, String> specValue2 = spec2.getValue();
//specValue1.forEach((v,k)->
//{if(specValue2.containsKey(v)){
//	System.out.println("ok");
//}
//});
		for (Map.Entry spec1Param : specValue1.entrySet()) {
			// verifie les key de spec 1 dans list spec2
			if (specValue2.containsKey(spec1Param.getKey())) {
				if (!specValue2.get(spec1Param.getKey()).equals(spec1Param.getValue())) {
					System.out.println(
							"la valure different  " + spec1Param.getValue() + " de key " + spec1Param.getKey());
				}

			} else {
				System.out.println("le parametre oublie'" + spec1Param.getKey() + "' dans " + spec2.getKey());
			}
		}
		System.out.println("+++++++++++++++++++++++++++++++++++++++");
		for (Map.Entry spec2Param : specValue2.entrySet()) { //
			if (!specValue1.containsKey(spec2Param.getKey())) {
				System.out.println("le parametre oublie'" + spec2Param.getKey() + "' dans " + spec1.getKey());

			} else {

			}

		}
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