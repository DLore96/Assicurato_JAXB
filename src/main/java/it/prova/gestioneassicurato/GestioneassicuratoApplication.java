package it.prova.gestioneassicurato;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.gestioneassicurato.generated.Assicurati;
import it.prova.gestioneassicurato.model.Assicurato;
import it.prova.gestioneassicurato.service.AssicuratoService;

@SpringBootApplication
public class GestioneassicuratoApplication implements CommandLineRunner {
	
	@Autowired
	private AssicuratoService assicuratoService;

	public static void main(String[] args) {
		SpringApplication.run(GestioneassicuratoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		 File file = new File("assicurati.xml");          
	        List<it.prova.gestioneassicurato.model.Assicurato> assicurati= doUnMarshall(file);
	        for(it.prova.gestioneassicurato.model.Assicurato app: assicurati) {
	        	System.out.println(app.getNome()+" "+ app.getCognome()+ " "+ app.getNuoviSinistri()+ "  "+ app.getDataNascita()+ app.getCodiceFiscale());
	        	if(app.getNuoviSinistri().intValue()>2) {
	        		System.out.println("non va bene");
	        		
	        	}else {
	        		Assicurato assicuratoModel = assicuratoService.cercaPerNomeECognome(app.getNome(), app.getCognome());
	        		if(assicuratoModel==null) {
	        			assicuratoService.inserisciNuovo(app);
	        		}else {
	        			app.setId(assicuratoModel.getId());
	        			assicuratoService.aggiorna(app);
	        		}
	        	}
	        	
	        }
	        
	        
	}
	
	private List<it.prova.gestioneassicurato.model.Assicurato>doUnMarshall(File file) throws Exception {
		
		JAXBContext jaxbContext = JAXBContext.newInstance(Assicurati.class);  
		   
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();  
        Assicurati listaAssicuratiPojo= (Assicurati) jaxbUnmarshaller.unmarshal(file); 
        List<it.prova.gestioneassicurato.generated.Assicurato> assicurati = listaAssicuratiPojo.getAssicurato();
        List<it.prova.gestioneassicurato.model.Assicurato> assicuratiModel= new ArrayList<it.prova.gestioneassicurato.model.Assicurato>();
      
        for(it.prova.gestioneassicurato.generated.Assicurato appItem: assicurati) {
        	it.prova.gestioneassicurato.model.Assicurato itemUploader= new it.prova.gestioneassicurato.model.Assicurato();
        	itemUploader.setNome(appItem.getNome());
        	itemUploader.setCognome(appItem.getCognome());
        	itemUploader.setCodiceFiscale(appItem.getCodicefiscale());
        	itemUploader.setNuoviSinistri(appItem.getNuovisinistri());
        	itemUploader.setDataNascita(appItem.getDatanascita().toGregorianCalendar().getTime());
        	assicuratiModel.add(itemUploader);
        }
        
        return assicuratiModel;
        
	}
	
	private void doMarshall(List<Assicurato> assicurati) throws Exception{
		
		//CONVERTO DA LIST MODEL A LIST POJO
		List<it.prova.gestioneassicurato.generated.Assicurato> assicuratiPojo= 
				new ArrayList<it.prova.gestioneassicurato.generated.Assicurato>();
		for(Assicurato assItemApp : assicurati) {
			it.prova.gestioneassicurato.generated.Assicurato assicuratoDaCaricare= 
					new it.prova.gestioneassicurato.generated.Assicurato();
			assicuratoDaCaricare.setNome(assItemApp.getNome());
			assicuratoDaCaricare.setCognome(assItemApp.getCognome());
			assicuratoDaCaricare.setNuovisinistri(assItemApp.getNuoviSinistri());
			
			//PER LE DATE 
			 GregorianCalendar c = new GregorianCalendar();
	         XMLGregorianCalendar xmlGregorianCalendar = null;
	         c.setTime(assItemApp.getDataNascita());
	         try {
	             xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
	         } catch (DatatypeConfigurationException e) {
	             e.printStackTrace();
	         }
	        assicuratoDaCaricare.setDatanascita(xmlGregorianCalendar);
	        assicuratiPojo.add(assicuratoDaCaricare);
			
		}
		//FINE CONVERSIONE
		
		//CONVERTO IN XML E INVIO AL FILE 
		
		 BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("scartati.xml"));
	        JAXBContext jContext = JAXBContext.newInstance(Assicurati.class);
	        Marshaller marshaller = jContext.createMarshaller();
	        Assicurati assicuratiJAXB = new Assicurati();
	        assicuratiJAXB.setAssicurato(assicuratiPojo);
	        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	        marshaller.marshal(assicuratiJAXB, bufferedWriter);
	        
	        
	}

}
