package com.sqli.stage.propertyfilemanager.compare;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.sqli.stage.propertyfilemanager.dto.ParametreRepository;
import com.sqli.stage.propertyfilemanager.dto.StatusRepository;
import com.sqli.stage.propertyfilemanager.dto.ValueRepository;
import com.sqli.stage.propertyfilemanager.entities.Parametre;
import com.sqli.stage.propertyfilemanager.entities.Propertie;
import com.sqli.stage.propertyfilemanager.entities.Status;
import com.sqli.stage.propertyfilemanager.entities.Value;
import com.sqli.stage.propertyfilemanager.service.PropertieService;

@Component
public class TraitementFile {

	@org.springframework.beans.factory.annotation.Value("${propertie.commun.name}")
	private String nameFilePropertyCommun;

	@Autowired
	private StatusRepository statusRepository;
	@Autowired
	private ValueRepository valueRepository;
	@Autowired
	ParametreRepository parametreRepository;
	@Autowired
	private PropertieService propertieService;
	

	public TraitementFile() {
		super();

	}

	/*
	 * 
	 * method for scanned file property .
	 * 
	 */

	public Map<String, Properties> scannedFile(MultipartFile file) throws IOException {
		BufferedInputStream bis = new BufferedInputStream(file.getInputStream());
		ZipInputStream zis = new ZipInputStream(bis);
		ZipEntry zipEntry = zis.getNextEntry();
		Properties filePropertie;
		Map<String, Properties> listPropertie = new HashMap<String, Properties>();
		while (zipEntry != null) {
			filePropertie = new Properties();
			filePropertie.load(zis);
			listPropertie.put(zipEntry.getName(), filePropertie);
			zipEntry = zis.getNextEntry();
		}
		zis.close();
		return listPropertie;

	}
	/*
	 * arshive file property
	 */

	public void arshiveFileProperty(MultipartFile file) throws IOException {
		BufferedInputStream bis = new BufferedInputStream(file.getInputStream());
		ZipInputStream zis = new ZipInputStream(bis);
		ZipEntry zipEntry = zis.getNextEntry();
		byte[] buffer = new byte[1024];
		while (zipEntry != null) {
			String filePath = "C:/Users/pc 12/eclipse-workspace/PropertyFileManager/uploadFile" + java.io.File.separator
					+ zipEntry.getName();
			if (!zipEntry.isDirectory()) {
				FileOutputStream fos = new FileOutputStream(filePath);
				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				fos.close();
			} else {
				java.io.File dir = new java.io.File(filePath);
				dir.mkdir();
			}
			zis.closeEntry();
			zipEntry = zis.getNextEntry();
		}
		zis.closeEntry();
		zis.close();

	}
	/*
	 * 
	 */

	public Map<String, Properties> addProtertieCommunToSpec(Map<String, Properties> listFile) {
		Map<String, Properties> propertyCommun = new HashMap<String, Properties>();
		Map<String, Properties> propertySpec = new HashMap<String, Properties>();
		listFile.forEach((k, V) -> {
			if (k.equals(nameFilePropertyCommun)) {
				propertyCommun.put(k, V);
			} else {
				propertySpec.put(k, V);
				List<Propertie> xx = propertieService.getAllPropertie();
				xx.forEach((s) -> {
					s.getId();
				});
			}
		});
		propertyCommun.forEach((K, V) -> {
			V.forEach((k, v) -> {

				propertySpec.forEach((kspec, Vspec) -> {
					if (!Vspec.containsKey(k)) {
						Vspec.put(k, v);

					}
				});
			});
		});

		return propertySpec;

	}

	public List<Parametre> addParametre(Map<String, Properties> listFileProperty) {
		Set<String> listeParametreGlobale = new HashSet<String>();
		List<Parametre> listParametreEntities = new ArrayList<Parametre>();
		listFileProperty.values().forEach((K) -> {
			K.forEach((k, v) -> {
				listeParametreGlobale.add(k.toString());
			});

		});
		listeParametreGlobale.forEach((s) -> {
			Parametre parametre = new Parametre(0, s);
			parametreRepository.save(parametre);
			listParametreEntities.add(parametre);

		});

		return listParametreEntities;
	}

//
////	
//	  Compare specific files
//	  
//	 



	public void compareFile(Map<String, Properties> listFileProperty, List<Propertie> prop, List<Parametre> param) {
		Status statusDiff = new Status(0, "differant");
		statusRepository.save(statusDiff);
		Status statusNormal = new Status(0, "normal");
		statusRepository.save(statusNormal);
		Status statusOublie = new Status(0, "oublie");
		statusRepository.save(statusOublie);
		Propertie propertie = null;
		Parametre parametre = null;
		Value value;
		for (Map.Entry<String, Properties> file1Spec : listFileProperty.entrySet()) {
			for (Map.Entry<String, Properties> file2Spec : listFileProperty.entrySet()) {
				if (!file1Spec.getKey().equals(file2Spec.getKey())) {

					for (Entry propertieSpec2 : file2Spec.getValue().entrySet()) {
						if (file1Spec.getValue().containsKey(propertieSpec2.getKey())) {
							if (!file1Spec.getValue().get(propertieSpec2.getKey()).equals(propertieSpec2.getValue())) {
								propertie = searchPropertie(prop, file2Spec.getKey());
								parametre = searchParametre(param, propertieSpec2.getKey().toString());
								value = new Value(propertieSpec2.getValue().toString(), parametre, statusDiff, propertie);

							} else {
								propertie = searchPropertie(prop, file2Spec.getKey());
								parametre = searchParametre(param, propertieSpec2.getKey().toString());
								value = new Value(propertieSpec2.getValue().toString(), parametre, statusNormal, propertie);
							}

						} else {
							propertie = searchPropertie(prop, file2Spec.getKey());
							parametre = searchParametre(param, propertieSpec2.getKey().toString());
							value = new Value(propertieSpec2.getValue().toString(), parametre, statusOublie, propertie);

						}

						valueRepository.save(value);

					}
				}

			}

		}

	}

	public Propertie searchPropertie(List<Propertie> prop, String keyPropertie) {
		Propertie propertieEntitie = new Propertie();
		for (Propertie p : prop) {

			if (p.getName().equals(keyPropertie)) {

				propertieEntitie = p;
			}
		}
		return propertieEntitie;

	}

	public Parametre searchParametre(List<Parametre> pram, String keyParam) {
		Parametre parametreEntitie = new Parametre();
		for (Parametre par : pram) {

			if (par.getParametrekey().equals(keyParam)) {

				parametreEntitie = par;
			}
		}
		return parametreEntitie;

	}

}