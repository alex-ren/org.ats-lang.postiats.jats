
#
# A simple Makefile
#

######

MAKE=make

######

PATSCC=$(PATSHOME)/bin/patscc
PATSOPT=$(PATSHOME)/bin/patsopt

######

ATSPKGRELOC=$(PATSHOME)/bin/atspkgreloc_curl

######

test: test_remote.dats ; \
$(PATSCC) -DATS ATSPKGRELOCROOT=\\\".TEST\\\" -o $@ $<

######
#
test02: \
test02.dats ; \
$(PATSCC) -IIATS ./.TEST02 \
-DATS_MEMALLOC_LIBC -DATS ATSPKGRELOCROOT=\\\".TEST02\\\" -o $@ $< -lgmp
#
######
#
test03: \
test03.dats ; \
$(PATSCC) -IIATS ./.TEST03 \
-DATS_MEMALLOC_LIBC -DATS ATSPKGRELOCROOT=\\\".TEST03\\\" -o $@ $< -lpcre
#
######

assignment0: assignment0.dats ; $(PATSCC) -D_GNU_SOURCE -o $@ $<

######
#
test-pkgreloc: \
test-pkgreloc.json ; $(ATSPKGRELOC) ./$<
test-pkgreloc.json: ; \
$(PATSOPT) --pkgreloc \
-DATS ATSPKGRELOCROOT=\".TEST\" --output-w $@ -d test_remote.dats
#
test02-pkgreloc: \
test02-pkgreloc.json ; $(ATSPKGRELOC) ./$<
test02-pkgreloc.json: ; \
$(PATSOPT) --pkgreloc \
-DATS ATSPKGRELOCROOT=\".TEST02\" --output-w $@ -d test02.dats
#
#
assignment0-pkgreloc:: ; \
  $(PATSOPT) --pkgreloc -d assignment0.dats | $(ATSPKGRELOC)
#
######

RMF=rm -f
RMRF=rm -rf

######

clean:: ; $(RMF) *~
clean:: ; $(RMF) *_?ats.o
clean:: ; $(RMF) *_?ats.c

######

cleanall:: clean
cleanall:: ; $(RMF) test01
cleanall:: ; $(RMRF) .TEST01
cleanall:: ; $(RMF) test02
cleanall:: ; $(RMRF) .TEST02
cleanall:: ; $(RMF) test03
cleanall:: ; $(RMRF) .TEST03
cleanall:: ; $(RMF) assignment0
cleanall:: ; $(RMRF) .CS320WEBROOT
cleanall:: ; $(RMF) test01-pkgreloc.json
cleanall:: ; $(RMF) test02-pkgreloc.json
cleanall:: ; $(RMF) test03-pkgreloc.mk
cleanall:: ; $(RMF) test03-pkgreloc.json

###### end of [Makefile] ######
