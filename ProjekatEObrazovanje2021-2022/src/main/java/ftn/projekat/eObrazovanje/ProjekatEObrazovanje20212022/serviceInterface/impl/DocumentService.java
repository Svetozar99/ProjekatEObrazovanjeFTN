package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.impl;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Document;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.repository.DocumentRepository;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.DocumentServiceInterface;

@Service
public class DocumentService implements DocumentServiceInterface {

	private static String DOCUMENTS_DIR_PATH;
	
	static {
		ResourceBundle rb=ResourceBundle.getBundle("application");
		DOCUMENTS_DIR_PATH=rb.getString("documentsDir");
	}
	
	@Autowired
	DocumentRepository documentRepository;
	
	@Override
	public List<Document> findAll() {
		// TODO Auto-generated method stub
		return documentRepository.findAll();
	}

	@Override
	public Document findById(Long id) {
		// TODO Auto-generated method stub
		return documentRepository.getOne(id);
	}

	@Override
	public Document save(Document document) {
		// TODO Auto-generated method stub
		return documentRepository.save(document);
	}

	@Override
	public void delete(Long id) {
		documentRepository.deleteById(id);
	}

	@Override
	public List<Document> findByUsername(String username) {
		// TODO Auto-generated method stub
		return documentRepository.findByStudent_user_username(username);
	}
	
	@Override
	public String saveUploadedFile(MultipartFile file) throws IOException {
    	String retVal = null;
    	System.out.println("Name: "+file.getName());
        if (! file.isEmpty()) {
            byte[] bytes = file.getBytes();
            File f = new File(DOCUMENTS_DIR_PATH);
            Path path = Paths.get(f.getAbsolutePath() + File.separator + file.getOriginalFilename());
            Files.write(path, bytes);
            retVal = path.toString();
        }
        return retVal;
    }
	
//	private File getResourceFilePath(String path) {
//		System.out.println("\n1");
//	    URL url = this.getClass().getClassLoader().getResource(path);
//	    System.out.println("\n2");
//	    File file = null;
//	    System.out.println("\n3");
//	    try {
//	    	System.out.println("\nUrl: "+url);
//	        file = new File(url.toURI());
//	    } catch (URISyntaxException e) {
//	        file = new File(url.getPath());
//	    }   
//	    return file;
//	}

}
