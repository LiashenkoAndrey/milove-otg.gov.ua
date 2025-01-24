package gov.milove.main.dto;

public interface DocumentDto {

    Long getId();
    String getTitle();

    String getName();

    DocGroupWithOnlyId getDocumentGroup();
}
