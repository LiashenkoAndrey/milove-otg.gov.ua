package gov.milove.services;


import gov.milove.domain.Document;
import gov.milove.domain.SubGroup;
import gov.milove.repositories.DocumentRepository;
import gov.milove.repositories.DocumentRepositoryMongo;
import gov.milove.repositories.DocumentSubGroupRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class DocumentService {

    private final DocumentRepositoryMongo documentRepositoryMongo;
    private final DocumentRepository documentRepository;
    private final DocumentSubGroupRepository subGroupRepository;

    public DocumentService(DocumentRepositoryMongo documentRepositoryMongo, DocumentRepository documentRepository, DocumentSubGroupRepository subGroupRepository) {
        this.documentRepositoryMongo = documentRepositoryMongo;
        this.documentRepository = documentRepository;
        this.subGroupRepository = subGroupRepository;
    }

    public void createDocument(MultipartFile file, String title, Long subGroupId) {
        SubGroup subGroup = subGroupRepository.findById(subGroupId).orElseThrow(EntityExistsException::new);
        String id = documentRepositoryMongo.saveToMongo(file, title);
        Document newDocument = Document.builder()
                .title(title)
                .document_id(id)
                .sub_group(subGroup)
                .build();

        documentRepository.save(newDocument);
        System.out.println("Document saved successfully!");
    }
}
