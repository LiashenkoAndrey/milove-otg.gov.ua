package gov.milove.forum.model.dto;

public interface ChatMetadataDto {

    Long getLast_read_message_id();
    Long getUnread_messages_count();

    Boolean getIs_member();
}
