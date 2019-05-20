CREATE TABLE "poi" (
	"id" SERIAL NOT NULL,
	"descpoi" TEXT NOT NULL,
	"coordenadax" INTEGER NOT NULL,
	"coordenaday" INTEGER NOT NULL,
	CONSTRAINT Usuario_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);