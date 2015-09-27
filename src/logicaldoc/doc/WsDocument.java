/**
 * WsDocument.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package logicaldoc.doc;

public class WsDocument  implements java.io.Serializable {
    private java.lang.String comment;

    private java.lang.String coverage;

    private java.lang.String creation;

    private java.lang.String creator;

    private long creatorId;

    private java.lang.String customId;

    private java.lang.String date;

    private int dateCategory;

    private java.lang.Long deleteUserId;

    private java.lang.String digest;

    private java.lang.Long docRef;

    private int docType;

    private java.lang.Long exportId;

    private java.lang.String exportName;

    private int exportStatus;

    private java.lang.String exportVersion;

    private logicaldoc.doc.WsAttribute[] extendedAttributes;

    private java.lang.String fileName;

    private long fileSize;

    private java.lang.String fileVersion;

    private java.lang.Long folderId;

    private java.lang.String icon;

    private long id;

    private int immutable;

    private int indexed;

    private java.lang.String language;

    private java.lang.String lastModified;

    private int lengthCategory;

    private java.lang.Long lockUserId;

    private java.lang.String object;

    private java.lang.String path;

    private java.lang.String publisher;

    private long publisherId;

    private java.lang.Integer rating;

    private java.lang.String recipient;

    private java.lang.Integer score;

    private int signed;

    private long size;

    private java.lang.String source;

    private java.lang.String sourceAuthor;

    private java.lang.String sourceDate;

    private java.lang.String sourceId;

    private java.lang.String sourceType;

    private int status;

    private java.lang.String summary;

    private java.lang.String[] tags;

    private java.lang.Long templateId;

    private java.lang.String title;

    private java.lang.String type;

    private java.lang.String version;

    public WsDocument() {
    }

    public WsDocument(
           java.lang.String comment,
           java.lang.String coverage,
           java.lang.String creation,
           java.lang.String creator,
           long creatorId,
           java.lang.String customId,
           java.lang.String date,
           int dateCategory,
           java.lang.Long deleteUserId,
           java.lang.String digest,
           java.lang.Long docRef,
           int docType,
           java.lang.Long exportId,
           java.lang.String exportName,
           int exportStatus,
           java.lang.String exportVersion,
           logicaldoc.doc.WsAttribute[] extendedAttributes,
           java.lang.String fileName,
           long fileSize,
           java.lang.String fileVersion,
           java.lang.Long folderId,
           java.lang.String icon,
           long id,
           int immutable,
           int indexed,
           java.lang.String language,
           java.lang.String lastModified,
           int lengthCategory,
           java.lang.Long lockUserId,
           java.lang.String object,
           java.lang.String path,
           java.lang.String publisher,
           long publisherId,
           java.lang.Integer rating,
           java.lang.String recipient,
           java.lang.Integer score,
           int signed,
           long size,
           java.lang.String source,
           java.lang.String sourceAuthor,
           java.lang.String sourceDate,
           java.lang.String sourceId,
           java.lang.String sourceType,
           int status,
           java.lang.String summary,
           java.lang.String[] tags,
           java.lang.Long templateId,
           java.lang.String title,
           java.lang.String type,
           java.lang.String version) {
           this.comment = comment;
           this.coverage = coverage;
           this.creation = creation;
           this.creator = creator;
           this.creatorId = creatorId;
           this.customId = customId;
           this.date = date;
           this.dateCategory = dateCategory;
           this.deleteUserId = deleteUserId;
           this.digest = digest;
           this.docRef = docRef;
           this.docType = docType;
           this.exportId = exportId;
           this.exportName = exportName;
           this.exportStatus = exportStatus;
           this.exportVersion = exportVersion;
           this.extendedAttributes = extendedAttributes;
           this.fileName = fileName;
           this.fileSize = fileSize;
           this.fileVersion = fileVersion;
           this.folderId = folderId;
           this.icon = icon;
           this.id = id;
           this.immutable = immutable;
           this.indexed = indexed;
           this.language = language;
           this.lastModified = lastModified;
           this.lengthCategory = lengthCategory;
           this.lockUserId = lockUserId;
           this.object = object;
           this.path = path;
           this.publisher = publisher;
           this.publisherId = publisherId;
           this.rating = rating;
           this.recipient = recipient;
           this.score = score;
           this.signed = signed;
           this.size = size;
           this.source = source;
           this.sourceAuthor = sourceAuthor;
           this.sourceDate = sourceDate;
           this.sourceId = sourceId;
           this.sourceType = sourceType;
           this.status = status;
           this.summary = summary;
           this.tags = tags;
           this.templateId = templateId;
           this.title = title;
           this.type = type;
           this.version = version;
    }


    /**
     * Gets the comment value for this WsDocument.
     * 
     * @return comment
     */
    public java.lang.String getComment() {
        return comment;
    }


    /**
     * Sets the comment value for this WsDocument.
     * 
     * @param comment
     */
    public void setComment(java.lang.String comment) {
        this.comment = comment;
    }


    /**
     * Gets the coverage value for this WsDocument.
     * 
     * @return coverage
     */
    public java.lang.String getCoverage() {
        return coverage;
    }


    /**
     * Sets the coverage value for this WsDocument.
     * 
     * @param coverage
     */
    public void setCoverage(java.lang.String coverage) {
        this.coverage = coverage;
    }


    /**
     * Gets the creation value for this WsDocument.
     * 
     * @return creation
     */
    public java.lang.String getCreation() {
        return creation;
    }


    /**
     * Sets the creation value for this WsDocument.
     * 
     * @param creation
     */
    public void setCreation(java.lang.String creation) {
        this.creation = creation;
    }


    /**
     * Gets the creator value for this WsDocument.
     * 
     * @return creator
     */
    public java.lang.String getCreator() {
        return creator;
    }


    /**
     * Sets the creator value for this WsDocument.
     * 
     * @param creator
     */
    public void setCreator(java.lang.String creator) {
        this.creator = creator;
    }


    /**
     * Gets the creatorId value for this WsDocument.
     * 
     * @return creatorId
     */
    public long getCreatorId() {
        return creatorId;
    }


    /**
     * Sets the creatorId value for this WsDocument.
     * 
     * @param creatorId
     */
    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
    }


    /**
     * Gets the customId value for this WsDocument.
     * 
     * @return customId
     */
    public java.lang.String getCustomId() {
        return customId;
    }


    /**
     * Sets the customId value for this WsDocument.
     * 
     * @param customId
     */
    public void setCustomId(java.lang.String customId) {
        this.customId = customId;
    }


    /**
     * Gets the date value for this WsDocument.
     * 
     * @return date
     */
    public java.lang.String getDate() {
        return date;
    }


    /**
     * Sets the date value for this WsDocument.
     * 
     * @param date
     */
    public void setDate(java.lang.String date) {
        this.date = date;
    }


    /**
     * Gets the dateCategory value for this WsDocument.
     * 
     * @return dateCategory
     */
    public int getDateCategory() {
        return dateCategory;
    }


    /**
     * Sets the dateCategory value for this WsDocument.
     * 
     * @param dateCategory
     */
    public void setDateCategory(int dateCategory) {
        this.dateCategory = dateCategory;
    }


    /**
     * Gets the deleteUserId value for this WsDocument.
     * 
     * @return deleteUserId
     */
    public java.lang.Long getDeleteUserId() {
        return deleteUserId;
    }


    /**
     * Sets the deleteUserId value for this WsDocument.
     * 
     * @param deleteUserId
     */
    public void setDeleteUserId(java.lang.Long deleteUserId) {
        this.deleteUserId = deleteUserId;
    }


    /**
     * Gets the digest value for this WsDocument.
     * 
     * @return digest
     */
    public java.lang.String getDigest() {
        return digest;
    }


    /**
     * Sets the digest value for this WsDocument.
     * 
     * @param digest
     */
    public void setDigest(java.lang.String digest) {
        this.digest = digest;
    }


    /**
     * Gets the docRef value for this WsDocument.
     * 
     * @return docRef
     */
    public java.lang.Long getDocRef() {
        return docRef;
    }


    /**
     * Sets the docRef value for this WsDocument.
     * 
     * @param docRef
     */
    public void setDocRef(java.lang.Long docRef) {
        this.docRef = docRef;
    }


    /**
     * Gets the docType value for this WsDocument.
     * 
     * @return docType
     */
    public int getDocType() {
        return docType;
    }


    /**
     * Sets the docType value for this WsDocument.
     * 
     * @param docType
     */
    public void setDocType(int docType) {
        this.docType = docType;
    }


    /**
     * Gets the exportId value for this WsDocument.
     * 
     * @return exportId
     */
    public java.lang.Long getExportId() {
        return exportId;
    }


    /**
     * Sets the exportId value for this WsDocument.
     * 
     * @param exportId
     */
    public void setExportId(java.lang.Long exportId) {
        this.exportId = exportId;
    }


    /**
     * Gets the exportName value for this WsDocument.
     * 
     * @return exportName
     */
    public java.lang.String getExportName() {
        return exportName;
    }


    /**
     * Sets the exportName value for this WsDocument.
     * 
     * @param exportName
     */
    public void setExportName(java.lang.String exportName) {
        this.exportName = exportName;
    }


    /**
     * Gets the exportStatus value for this WsDocument.
     * 
     * @return exportStatus
     */
    public int getExportStatus() {
        return exportStatus;
    }


    /**
     * Sets the exportStatus value for this WsDocument.
     * 
     * @param exportStatus
     */
    public void setExportStatus(int exportStatus) {
        this.exportStatus = exportStatus;
    }


    /**
     * Gets the exportVersion value for this WsDocument.
     * 
     * @return exportVersion
     */
    public java.lang.String getExportVersion() {
        return exportVersion;
    }


    /**
     * Sets the exportVersion value for this WsDocument.
     * 
     * @param exportVersion
     */
    public void setExportVersion(java.lang.String exportVersion) {
        this.exportVersion = exportVersion;
    }


    /**
     * Gets the extendedAttributes value for this WsDocument.
     * 
     * @return extendedAttributes
     */
    public logicaldoc.doc.WsAttribute[] getExtendedAttributes() {
        return extendedAttributes;
    }


    /**
     * Sets the extendedAttributes value for this WsDocument.
     * 
     * @param extendedAttributes
     */
    public void setExtendedAttributes(logicaldoc.doc.WsAttribute[] extendedAttributes) {
        this.extendedAttributes = extendedAttributes;
    }

    public logicaldoc.doc.WsAttribute getExtendedAttributes(int i) {
        return this.extendedAttributes[i];
    }

    public void setExtendedAttributes(int i, logicaldoc.doc.WsAttribute _value) {
        this.extendedAttributes[i] = _value;
    }


    /**
     * Gets the fileName value for this WsDocument.
     * 
     * @return fileName
     */
    public java.lang.String getFileName() {
        return fileName;
    }


    /**
     * Sets the fileName value for this WsDocument.
     * 
     * @param fileName
     */
    public void setFileName(java.lang.String fileName) {
        this.fileName = fileName;
    }


    /**
     * Gets the fileSize value for this WsDocument.
     * 
     * @return fileSize
     */
    public long getFileSize() {
        return fileSize;
    }


    /**
     * Sets the fileSize value for this WsDocument.
     * 
     * @param fileSize
     */
    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }


    /**
     * Gets the fileVersion value for this WsDocument.
     * 
     * @return fileVersion
     */
    public java.lang.String getFileVersion() {
        return fileVersion;
    }


    /**
     * Sets the fileVersion value for this WsDocument.
     * 
     * @param fileVersion
     */
    public void setFileVersion(java.lang.String fileVersion) {
        this.fileVersion = fileVersion;
    }


    /**
     * Gets the folderId value for this WsDocument.
     * 
     * @return folderId
     */
    public java.lang.Long getFolderId() {
        return folderId;
    }


    /**
     * Sets the folderId value for this WsDocument.
     * 
     * @param folderId
     */
    public void setFolderId(java.lang.Long folderId) {
        this.folderId = folderId;
    }


    /**
     * Gets the icon value for this WsDocument.
     * 
     * @return icon
     */
    public java.lang.String getIcon() {
        return icon;
    }


    /**
     * Sets the icon value for this WsDocument.
     * 
     * @param icon
     */
    public void setIcon(java.lang.String icon) {
        this.icon = icon;
    }


    /**
     * Gets the id value for this WsDocument.
     * 
     * @return id
     */
    public long getId() {
        return id;
    }


    /**
     * Sets the id value for this WsDocument.
     * 
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }


    /**
     * Gets the immutable value for this WsDocument.
     * 
     * @return immutable
     */
    public int getImmutable() {
        return immutable;
    }


    /**
     * Sets the immutable value for this WsDocument.
     * 
     * @param immutable
     */
    public void setImmutable(int immutable) {
        this.immutable = immutable;
    }


    /**
     * Gets the indexed value for this WsDocument.
     * 
     * @return indexed
     */
    public int getIndexed() {
        return indexed;
    }


    /**
     * Sets the indexed value for this WsDocument.
     * 
     * @param indexed
     */
    public void setIndexed(int indexed) {
        this.indexed = indexed;
    }


    /**
     * Gets the language value for this WsDocument.
     * 
     * @return language
     */
    public java.lang.String getLanguage() {
        return language;
    }


    /**
     * Sets the language value for this WsDocument.
     * 
     * @param language
     */
    public void setLanguage(java.lang.String language) {
        this.language = language;
    }


    /**
     * Gets the lastModified value for this WsDocument.
     * 
     * @return lastModified
     */
    public java.lang.String getLastModified() {
        return lastModified;
    }


    /**
     * Sets the lastModified value for this WsDocument.
     * 
     * @param lastModified
     */
    public void setLastModified(java.lang.String lastModified) {
        this.lastModified = lastModified;
    }


    /**
     * Gets the lengthCategory value for this WsDocument.
     * 
     * @return lengthCategory
     */
    public int getLengthCategory() {
        return lengthCategory;
    }


    /**
     * Sets the lengthCategory value for this WsDocument.
     * 
     * @param lengthCategory
     */
    public void setLengthCategory(int lengthCategory) {
        this.lengthCategory = lengthCategory;
    }


    /**
     * Gets the lockUserId value for this WsDocument.
     * 
     * @return lockUserId
     */
    public java.lang.Long getLockUserId() {
        return lockUserId;
    }


    /**
     * Sets the lockUserId value for this WsDocument.
     * 
     * @param lockUserId
     */
    public void setLockUserId(java.lang.Long lockUserId) {
        this.lockUserId = lockUserId;
    }


    /**
     * Gets the object value for this WsDocument.
     * 
     * @return object
     */
    public java.lang.String getObject() {
        return object;
    }


    /**
     * Sets the object value for this WsDocument.
     * 
     * @param object
     */
    public void setObject(java.lang.String object) {
        this.object = object;
    }


    /**
     * Gets the path value for this WsDocument.
     * 
     * @return path
     */
    public java.lang.String getPath() {
        return path;
    }


    /**
     * Sets the path value for this WsDocument.
     * 
     * @param path
     */
    public void setPath(java.lang.String path) {
        this.path = path;
    }


    /**
     * Gets the publisher value for this WsDocument.
     * 
     * @return publisher
     */
    public java.lang.String getPublisher() {
        return publisher;
    }


    /**
     * Sets the publisher value for this WsDocument.
     * 
     * @param publisher
     */
    public void setPublisher(java.lang.String publisher) {
        this.publisher = publisher;
    }


    /**
     * Gets the publisherId value for this WsDocument.
     * 
     * @return publisherId
     */
    public long getPublisherId() {
        return publisherId;
    }


    /**
     * Sets the publisherId value for this WsDocument.
     * 
     * @param publisherId
     */
    public void setPublisherId(long publisherId) {
        this.publisherId = publisherId;
    }


    /**
     * Gets the rating value for this WsDocument.
     * 
     * @return rating
     */
    public java.lang.Integer getRating() {
        return rating;
    }


    /**
     * Sets the rating value for this WsDocument.
     * 
     * @param rating
     */
    public void setRating(java.lang.Integer rating) {
        this.rating = rating;
    }


    /**
     * Gets the recipient value for this WsDocument.
     * 
     * @return recipient
     */
    public java.lang.String getRecipient() {
        return recipient;
    }


    /**
     * Sets the recipient value for this WsDocument.
     * 
     * @param recipient
     */
    public void setRecipient(java.lang.String recipient) {
        this.recipient = recipient;
    }


    /**
     * Gets the score value for this WsDocument.
     * 
     * @return score
     */
    public java.lang.Integer getScore() {
        return score;
    }


    /**
     * Sets the score value for this WsDocument.
     * 
     * @param score
     */
    public void setScore(java.lang.Integer score) {
        this.score = score;
    }


    /**
     * Gets the signed value for this WsDocument.
     * 
     * @return signed
     */
    public int getSigned() {
        return signed;
    }


    /**
     * Sets the signed value for this WsDocument.
     * 
     * @param signed
     */
    public void setSigned(int signed) {
        this.signed = signed;
    }


    /**
     * Gets the size value for this WsDocument.
     * 
     * @return size
     */
    public long getSize() {
        return size;
    }


    /**
     * Sets the size value for this WsDocument.
     * 
     * @param size
     */
    public void setSize(long size) {
        this.size = size;
    }


    /**
     * Gets the source value for this WsDocument.
     * 
     * @return source
     */
    public java.lang.String getSource() {
        return source;
    }


    /**
     * Sets the source value for this WsDocument.
     * 
     * @param source
     */
    public void setSource(java.lang.String source) {
        this.source = source;
    }


    /**
     * Gets the sourceAuthor value for this WsDocument.
     * 
     * @return sourceAuthor
     */
    public java.lang.String getSourceAuthor() {
        return sourceAuthor;
    }


    /**
     * Sets the sourceAuthor value for this WsDocument.
     * 
     * @param sourceAuthor
     */
    public void setSourceAuthor(java.lang.String sourceAuthor) {
        this.sourceAuthor = sourceAuthor;
    }


    /**
     * Gets the sourceDate value for this WsDocument.
     * 
     * @return sourceDate
     */
    public java.lang.String getSourceDate() {
        return sourceDate;
    }


    /**
     * Sets the sourceDate value for this WsDocument.
     * 
     * @param sourceDate
     */
    public void setSourceDate(java.lang.String sourceDate) {
        this.sourceDate = sourceDate;
    }


    /**
     * Gets the sourceId value for this WsDocument.
     * 
     * @return sourceId
     */
    public java.lang.String getSourceId() {
        return sourceId;
    }


    /**
     * Sets the sourceId value for this WsDocument.
     * 
     * @param sourceId
     */
    public void setSourceId(java.lang.String sourceId) {
        this.sourceId = sourceId;
    }


    /**
     * Gets the sourceType value for this WsDocument.
     * 
     * @return sourceType
     */
    public java.lang.String getSourceType() {
        return sourceType;
    }


    /**
     * Sets the sourceType value for this WsDocument.
     * 
     * @param sourceType
     */
    public void setSourceType(java.lang.String sourceType) {
        this.sourceType = sourceType;
    }


    /**
     * Gets the status value for this WsDocument.
     * 
     * @return status
     */
    public int getStatus() {
        return status;
    }


    /**
     * Sets the status value for this WsDocument.
     * 
     * @param status
     */
    public void setStatus(int status) {
        this.status = status;
    }


    /**
     * Gets the summary value for this WsDocument.
     * 
     * @return summary
     */
    public java.lang.String getSummary() {
        return summary;
    }


    /**
     * Sets the summary value for this WsDocument.
     * 
     * @param summary
     */
    public void setSummary(java.lang.String summary) {
        this.summary = summary;
    }


    /**
     * Gets the tags value for this WsDocument.
     * 
     * @return tags
     */
    public java.lang.String[] getTags() {
        return tags;
    }


    /**
     * Sets the tags value for this WsDocument.
     * 
     * @param tags
     */
    public void setTags(java.lang.String[] tags) {
        this.tags = tags;
    }

    public java.lang.String getTags(int i) {
        return this.tags[i];
    }

    public void setTags(int i, java.lang.String _value) {
        this.tags[i] = _value;
    }


    /**
     * Gets the templateId value for this WsDocument.
     * 
     * @return templateId
     */
    public java.lang.Long getTemplateId() {
        return templateId;
    }


    /**
     * Sets the templateId value for this WsDocument.
     * 
     * @param templateId
     */
    public void setTemplateId(java.lang.Long templateId) {
        this.templateId = templateId;
    }


    /**
     * Gets the title value for this WsDocument.
     * 
     * @return title
     */
    public java.lang.String getTitle() {
        return title;
    }


    /**
     * Sets the title value for this WsDocument.
     * 
     * @param title
     */
    public void setTitle(java.lang.String title) {
        this.title = title;
    }


    /**
     * Gets the type value for this WsDocument.
     * 
     * @return type
     */
    public java.lang.String getType() {
        return type;
    }


    /**
     * Sets the type value for this WsDocument.
     * 
     * @param type
     */
    public void setType(java.lang.String type) {
        this.type = type;
    }


    /**
     * Gets the version value for this WsDocument.
     * 
     * @return version
     */
    public java.lang.String getVersion() {
        return version;
    }


    /**
     * Sets the version value for this WsDocument.
     * 
     * @param version
     */
    public void setVersion(java.lang.String version) {
        this.version = version;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof WsDocument)) return false;
        WsDocument other = (WsDocument) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.comment==null && other.getComment()==null) || 
             (this.comment!=null &&
              this.comment.equals(other.getComment()))) &&
            ((this.coverage==null && other.getCoverage()==null) || 
             (this.coverage!=null &&
              this.coverage.equals(other.getCoverage()))) &&
            ((this.creation==null && other.getCreation()==null) || 
             (this.creation!=null &&
              this.creation.equals(other.getCreation()))) &&
            ((this.creator==null && other.getCreator()==null) || 
             (this.creator!=null &&
              this.creator.equals(other.getCreator()))) &&
            this.creatorId == other.getCreatorId() &&
            ((this.customId==null && other.getCustomId()==null) || 
             (this.customId!=null &&
              this.customId.equals(other.getCustomId()))) &&
            ((this.date==null && other.getDate()==null) || 
             (this.date!=null &&
              this.date.equals(other.getDate()))) &&
            this.dateCategory == other.getDateCategory() &&
            ((this.deleteUserId==null && other.getDeleteUserId()==null) || 
             (this.deleteUserId!=null &&
              this.deleteUserId.equals(other.getDeleteUserId()))) &&
            ((this.digest==null && other.getDigest()==null) || 
             (this.digest!=null &&
              this.digest.equals(other.getDigest()))) &&
            ((this.docRef==null && other.getDocRef()==null) || 
             (this.docRef!=null &&
              this.docRef.equals(other.getDocRef()))) &&
            this.docType == other.getDocType() &&
            ((this.exportId==null && other.getExportId()==null) || 
             (this.exportId!=null &&
              this.exportId.equals(other.getExportId()))) &&
            ((this.exportName==null && other.getExportName()==null) || 
             (this.exportName!=null &&
              this.exportName.equals(other.getExportName()))) &&
            this.exportStatus == other.getExportStatus() &&
            ((this.exportVersion==null && other.getExportVersion()==null) || 
             (this.exportVersion!=null &&
              this.exportVersion.equals(other.getExportVersion()))) &&
            ((this.extendedAttributes==null && other.getExtendedAttributes()==null) || 
             (this.extendedAttributes!=null &&
              java.util.Arrays.equals(this.extendedAttributes, other.getExtendedAttributes()))) &&
            ((this.fileName==null && other.getFileName()==null) || 
             (this.fileName!=null &&
              this.fileName.equals(other.getFileName()))) &&
            this.fileSize == other.getFileSize() &&
            ((this.fileVersion==null && other.getFileVersion()==null) || 
             (this.fileVersion!=null &&
              this.fileVersion.equals(other.getFileVersion()))) &&
            ((this.folderId==null && other.getFolderId()==null) || 
             (this.folderId!=null &&
              this.folderId.equals(other.getFolderId()))) &&
            ((this.icon==null && other.getIcon()==null) || 
             (this.icon!=null &&
              this.icon.equals(other.getIcon()))) &&
            this.id == other.getId() &&
            this.immutable == other.getImmutable() &&
            this.indexed == other.getIndexed() &&
            ((this.language==null && other.getLanguage()==null) || 
             (this.language!=null &&
              this.language.equals(other.getLanguage()))) &&
            ((this.lastModified==null && other.getLastModified()==null) || 
             (this.lastModified!=null &&
              this.lastModified.equals(other.getLastModified()))) &&
            this.lengthCategory == other.getLengthCategory() &&
            ((this.lockUserId==null && other.getLockUserId()==null) || 
             (this.lockUserId!=null &&
              this.lockUserId.equals(other.getLockUserId()))) &&
            ((this.object==null && other.getObject()==null) || 
             (this.object!=null &&
              this.object.equals(other.getObject()))) &&
            ((this.path==null && other.getPath()==null) || 
             (this.path!=null &&
              this.path.equals(other.getPath()))) &&
            ((this.publisher==null && other.getPublisher()==null) || 
             (this.publisher!=null &&
              this.publisher.equals(other.getPublisher()))) &&
            this.publisherId == other.getPublisherId() &&
            ((this.rating==null && other.getRating()==null) || 
             (this.rating!=null &&
              this.rating.equals(other.getRating()))) &&
            ((this.recipient==null && other.getRecipient()==null) || 
             (this.recipient!=null &&
              this.recipient.equals(other.getRecipient()))) &&
            ((this.score==null && other.getScore()==null) || 
             (this.score!=null &&
              this.score.equals(other.getScore()))) &&
            this.signed == other.getSigned() &&
            this.size == other.getSize() &&
            ((this.source==null && other.getSource()==null) || 
             (this.source!=null &&
              this.source.equals(other.getSource()))) &&
            ((this.sourceAuthor==null && other.getSourceAuthor()==null) || 
             (this.sourceAuthor!=null &&
              this.sourceAuthor.equals(other.getSourceAuthor()))) &&
            ((this.sourceDate==null && other.getSourceDate()==null) || 
             (this.sourceDate!=null &&
              this.sourceDate.equals(other.getSourceDate()))) &&
            ((this.sourceId==null && other.getSourceId()==null) || 
             (this.sourceId!=null &&
              this.sourceId.equals(other.getSourceId()))) &&
            ((this.sourceType==null && other.getSourceType()==null) || 
             (this.sourceType!=null &&
              this.sourceType.equals(other.getSourceType()))) &&
            this.status == other.getStatus() &&
            ((this.summary==null && other.getSummary()==null) || 
             (this.summary!=null &&
              this.summary.equals(other.getSummary()))) &&
            ((this.tags==null && other.getTags()==null) || 
             (this.tags!=null &&
              java.util.Arrays.equals(this.tags, other.getTags()))) &&
            ((this.templateId==null && other.getTemplateId()==null) || 
             (this.templateId!=null &&
              this.templateId.equals(other.getTemplateId()))) &&
            ((this.title==null && other.getTitle()==null) || 
             (this.title!=null &&
              this.title.equals(other.getTitle()))) &&
            ((this.type==null && other.getType()==null) || 
             (this.type!=null &&
              this.type.equals(other.getType()))) &&
            ((this.version==null && other.getVersion()==null) || 
             (this.version!=null &&
              this.version.equals(other.getVersion())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getComment() != null) {
            _hashCode += getComment().hashCode();
        }
        if (getCoverage() != null) {
            _hashCode += getCoverage().hashCode();
        }
        if (getCreation() != null) {
            _hashCode += getCreation().hashCode();
        }
        if (getCreator() != null) {
            _hashCode += getCreator().hashCode();
        }
        _hashCode += new Long(getCreatorId()).hashCode();
        if (getCustomId() != null) {
            _hashCode += getCustomId().hashCode();
        }
        if (getDate() != null) {
            _hashCode += getDate().hashCode();
        }
        _hashCode += getDateCategory();
        if (getDeleteUserId() != null) {
            _hashCode += getDeleteUserId().hashCode();
        }
        if (getDigest() != null) {
            _hashCode += getDigest().hashCode();
        }
        if (getDocRef() != null) {
            _hashCode += getDocRef().hashCode();
        }
        _hashCode += getDocType();
        if (getExportId() != null) {
            _hashCode += getExportId().hashCode();
        }
        if (getExportName() != null) {
            _hashCode += getExportName().hashCode();
        }
        _hashCode += getExportStatus();
        if (getExportVersion() != null) {
            _hashCode += getExportVersion().hashCode();
        }
        if (getExtendedAttributes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getExtendedAttributes());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getExtendedAttributes(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getFileName() != null) {
            _hashCode += getFileName().hashCode();
        }
        _hashCode += new Long(getFileSize()).hashCode();
        if (getFileVersion() != null) {
            _hashCode += getFileVersion().hashCode();
        }
        if (getFolderId() != null) {
            _hashCode += getFolderId().hashCode();
        }
        if (getIcon() != null) {
            _hashCode += getIcon().hashCode();
        }
        _hashCode += new Long(getId()).hashCode();
        _hashCode += getImmutable();
        _hashCode += getIndexed();
        if (getLanguage() != null) {
            _hashCode += getLanguage().hashCode();
        }
        if (getLastModified() != null) {
            _hashCode += getLastModified().hashCode();
        }
        _hashCode += getLengthCategory();
        if (getLockUserId() != null) {
            _hashCode += getLockUserId().hashCode();
        }
        if (getObject() != null) {
            _hashCode += getObject().hashCode();
        }
        if (getPath() != null) {
            _hashCode += getPath().hashCode();
        }
        if (getPublisher() != null) {
            _hashCode += getPublisher().hashCode();
        }
        _hashCode += new Long(getPublisherId()).hashCode();
        if (getRating() != null) {
            _hashCode += getRating().hashCode();
        }
        if (getRecipient() != null) {
            _hashCode += getRecipient().hashCode();
        }
        if (getScore() != null) {
            _hashCode += getScore().hashCode();
        }
        _hashCode += getSigned();
        _hashCode += new Long(getSize()).hashCode();
        if (getSource() != null) {
            _hashCode += getSource().hashCode();
        }
        if (getSourceAuthor() != null) {
            _hashCode += getSourceAuthor().hashCode();
        }
        if (getSourceDate() != null) {
            _hashCode += getSourceDate().hashCode();
        }
        if (getSourceId() != null) {
            _hashCode += getSourceId().hashCode();
        }
        if (getSourceType() != null) {
            _hashCode += getSourceType().hashCode();
        }
        _hashCode += getStatus();
        if (getSummary() != null) {
            _hashCode += getSummary().hashCode();
        }
        if (getTags() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTags());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTags(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTemplateId() != null) {
            _hashCode += getTemplateId().hashCode();
        }
        if (getTitle() != null) {
            _hashCode += getTitle().hashCode();
        }
        if (getType() != null) {
            _hashCode += getType().hashCode();
        }
        if (getVersion() != null) {
            _hashCode += getVersion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(WsDocument.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://document.webservice.logicaldoc.com/", "wsDocument"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comment");
        elemField.setXmlName(new javax.xml.namespace.QName("", "comment"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("coverage");
        elemField.setXmlName(new javax.xml.namespace.QName("", "coverage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "creation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creator");
        elemField.setXmlName(new javax.xml.namespace.QName("", "creator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creatorId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "creatorId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "customId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("date");
        elemField.setXmlName(new javax.xml.namespace.QName("", "date"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dateCategory");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dateCategory"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deleteUserId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "deleteUserId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("digest");
        elemField.setXmlName(new javax.xml.namespace.QName("", "digest"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("docRef");
        elemField.setXmlName(new javax.xml.namespace.QName("", "docRef"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("docType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "docType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("exportId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "exportId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("exportName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "exportName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("exportStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "exportStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("exportVersion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "exportVersion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extendedAttributes");
        elemField.setXmlName(new javax.xml.namespace.QName("", "extendedAttributes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://document.webservice.logicaldoc.com/", "wsAttribute"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fileName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fileName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fileSize");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fileSize"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fileVersion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fileVersion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("folderId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "folderId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("icon");
        elemField.setXmlName(new javax.xml.namespace.QName("", "icon"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("immutable");
        elemField.setXmlName(new javax.xml.namespace.QName("", "immutable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indexed");
        elemField.setXmlName(new javax.xml.namespace.QName("", "indexed"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("language");
        elemField.setXmlName(new javax.xml.namespace.QName("", "language"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastModified");
        elemField.setXmlName(new javax.xml.namespace.QName("", "lastModified"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lengthCategory");
        elemField.setXmlName(new javax.xml.namespace.QName("", "lengthCategory"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lockUserId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "lockUserId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("object");
        elemField.setXmlName(new javax.xml.namespace.QName("", "object"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("path");
        elemField.setXmlName(new javax.xml.namespace.QName("", "path"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("publisher");
        elemField.setXmlName(new javax.xml.namespace.QName("", "publisher"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("publisherId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "publisherId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rating");
        elemField.setXmlName(new javax.xml.namespace.QName("", "rating"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recipient");
        elemField.setXmlName(new javax.xml.namespace.QName("", "recipient"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("score");
        elemField.setXmlName(new javax.xml.namespace.QName("", "score"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("signed");
        elemField.setXmlName(new javax.xml.namespace.QName("", "signed"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("size");
        elemField.setXmlName(new javax.xml.namespace.QName("", "size"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("source");
        elemField.setXmlName(new javax.xml.namespace.QName("", "source"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sourceAuthor");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sourceAuthor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sourceDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sourceDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sourceId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sourceId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sourceType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sourceType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("", "status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("summary");
        elemField.setXmlName(new javax.xml.namespace.QName("", "summary"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tags");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tags"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("templateId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "templateId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("title");
        elemField.setXmlName(new javax.xml.namespace.QName("", "title"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("type");
        elemField.setXmlName(new javax.xml.namespace.QName("", "type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("version");
        elemField.setXmlName(new javax.xml.namespace.QName("", "version"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
