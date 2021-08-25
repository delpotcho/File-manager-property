package com.sqli.stage.propertyfilemanager.compare;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.sqli.stage.propertyfilemanager.dto.FileRepository;
import com.sqli.stage.propertyfilemanager.dto.ParametreRepository;
import com.sqli.stage.propertyfilemanager.dto.PropertieRepository;
import com.sqli.stage.propertyfilemanager.dto.StatusRepository;
import com.sqli.stage.propertyfilemanager.dto.ValueRepository;
import com.sqli.stage.propertyfilemanager.entities.File;
import com.sqli.stage.propertyfilemanager.entities.Parametre;
import com.sqli.stage.propertyfilemanager.entities.Propertie;
import com.sqli.stage.propertyfilemanager.entities.Status;
import com.sqli.stage.propertyfilemanager.entities.Value;

@Component
public class TraitementFile {

	@org.springframework.beans.factory.annotation.Value("${propertie.commun.name}")
	private String nameFilePropertyCommun;
	@Autowired
	private PropertieRepository propertieRepository;
	@Autowired
	private ParametreRepository parametreRepository;
	@Autowired
	private StatusRepository statusRepository;
	@Autowired
	private ValueRepository valueRepository;


	public TraitementFile() {
		super();

	}

	/*
	 * 
	 * method for scanned file property and ignore spacing and comments .
	 * 
	 */

	public Map<String, Properties> scannedFile(MultipartFile file) throws IOException {
		BufferedInputStream bis = new BufferedInputStream(file.getInputStream());
		ZipInputStream zis = new ZipInputStream(bis);
		ZipEntry zipEntry = zis.getNextEntry();
		byte[] buffer = new byte[1024];
		Properties filePropertie;
		Map<String, Properties> listPropertie = new HashMap<String, Properties>();
		while (zipEntry != null) {
			String filePath = "C:/Users/pc 12/eclipse-workspace/PropertyFileManager/uploadFile" + java.io.File.separator
					+ zipEntry.getName();
			filePropertie = new Properties();
			filePropertie.load(zis);
			// System.out.println(filePropertie.toString());
			listPropertie.put(zipEntry.getName(), filePropertie);
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
		return listPropertie;

	}


	public Map<String, Properties> addProtertieCommonToSpecifique(Map<String, Properties> listFile) {

		Map<String, Properties> propertyCommun = new HashMap<String, Properties>();
		Map<String, Properties> propertySpec = new HashMap<String, Properties>();
		listFile.forEach((k, V) -> {
			if (k.equals(nameFilePropertyCommun)) {
				propertyCommun.put(k, V);
			} else {
				propertySpec.put(k, V);
				List<Propertie> xx = propertieRepository.findAll();
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
//		System.out.println("++++++++++++++++++apres ++++++++++++++");
//		System.out.println(propertySpec);
		return propertySpec;

	}

	public List<Propertie> addPropertySpec(Map<String, Properties> listSpec, File folder) {

		List<Propertie> listePropertie = new ArrayList<Propertie>();
		listSpec.forEach((k, v) -> {
			Propertie p = new Propertie(0, k, "spec", folder);
			propertieRepository.save(p);
			listePropertie.add(p);
		});
		return listePropertie;
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
	Propertie propertieEntitie;
	Parametre parametreEntitie;

	public void compareFile(Map<String, Properties> listFileProperty, List<Propertie> prop, List<Parametre> param) {

		Status statusDiff = new Status(0, "differant");
		statusRepository.save(statusDiff);

		Status statusNormal = new Status(0, "normal");
		statusRepository.save(statusNormal);
		Status statusOublie = new Status(0, "oublie");
		statusRepository.save(statusOublie);

		listFileProperty.forEach((nomFileSpec, propertieSpec) -> {

			listFileProperty.forEach((nomFileSpec2, propertieSpec2) -> {

				if (!nomFileSpec.equals(nomFileSpec2)) {

					propertieSpec2.forEach((keyPropertie2, valuePropertie2) -> {

						// System.out.println("nom propertie " + nomFileSpec2 + " de parametre " +
						// keyPropertie2
						// + " sa valeur " + valuePropertie2);

						if (propertieSpec.containsKey(keyPropertie2)) {

							if (!propertieSpec.get(keyPropertie2).equals(valuePropertie2)) {
								prop.forEach((p) -> {
									if (p.getName().equals(nomFileSpec2)) {
										propertieEntitie = new Propertie();
										propertieEntitie = p;
									}
								});
								param.forEach((par) -> {

									if (par.getParametrekey().equals(keyPropertie2)) {
										parametreEntitie = new Parametre();
										parametreEntitie = par;
									}
								});
								// System.out.println("parametreEntitie "+ parametreEntitie.getParametrekey());
								// System.out.println("propertieEntitie "+ propertieEntitie.getName());
								Value v = new Value(valuePropertie2.toString(), parametreEntitie, statusDiff,
										propertieEntitie);
								valueRepository.save(v);

								// System.out.println(" la valure different ' " + valuePropertie2 + " ' de key "
								// +
								// keyPropertie2
								// + " dans file" + nomFileSpec2);

							} else {
								prop.forEach((p) -> {
									if (p.getName().equals(nomFileSpec2)) {
										propertieEntitie = new Propertie();
										propertieEntitie = p;
									}
								});
								param.forEach((par) -> {

									if (par.getParametrekey().equals(keyPropertie2)) {
										parametreEntitie = new Parametre();
										parametreEntitie = par;
									}
								});
								// System.out.println("parametreEntitie "+ parametreEntitie.getParametrekey());
								// System.out.println("propertieEntitie "+ propertieEntitie.getName());
								Value v = new Value(valuePropertie2.toString(), parametreEntitie, statusNormal,
										propertieEntitie);
								valueRepository.save(v);

							}

						} else {
							prop.forEach((p) -> {
								if (p.getName().equals(nomFileSpec2)) {
									propertieEntitie = new Propertie();
									propertieEntitie = p;
								}
							});
							param.forEach((par) -> {

								if (par.getParametrekey().equals(keyPropertie2)) {
									parametreEntitie = new Parametre();
									parametreEntitie = par;
								}
							});
							// System.out.println("parametreEntitie "+ parametreEntitie.getParametrekey());
							// System.out.println("propertieEntitie "+ propertieEntitie.getName());
							Value v = new Value(valuePropertie2.toString(), parametreEntitie, statusOublie,
									propertieEntitie);
							valueRepository.save(v);

							// System.out.println("le parametre oublie'" + keyPropertie2 + "' dans " +
							// nomFileSpec);
//
						}
					});
				}
			});

		});

	}

}