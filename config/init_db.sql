CREATE TABLE resume
(
  uuid      CHAR(36) NOT NULL
  CONSTRAINT resumes_pkey
  PRIMARY KEY,
  full_name TEXT NOT NULL
);

CREATE TABLE contact
(
  id          SERIAL   NOT NULL
    CONSTRAINT contact_pkey
    PRIMARY KEY,
  resume_uuid CHAR(36) NOT NULL
    REFERENCES resume
    ON DELETE CASCADE,
  type        TEXT     NOT NULL,
  value       TEXT     NOT NULL

);

CREATE UNIQUE INDEX contact_uuid_type_index
  ON contact (resume_uuid, type);
