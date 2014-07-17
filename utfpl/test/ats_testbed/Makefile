
#
# A simple Makefile
#

######

PATSCC=patscc
PATSOPT=patsopt

######

02: \
02.dats ; \
$(PATSCC) -D_GNU_SOURCE -DATS_MEMALLOC_LIBC -o $@ $^

05: \
05.dats ; \
$(PATSCC) -D_GNU_SOURCE -DATS_MEMALLOC_LIBC -o $@ $^

06: \
06.dats ; \
$(PATSCC) -D_GNU_SOURCE -DATS_MEMALLOC_LIBC -o $@ $^

07: \
07.dats ; \
$(PATSCC) -D_GNU_SOURCE -DATS_MEMALLOC_LIBC -o $@ $^

######


######

RMF=rm -f

######

clean:: ; $(RMF) *~
clean:: ; $(RMF) *_?ats.o
clean:: ; $(RMF) *_?ats.c

cleanall:: clean
cleanall:: ; $(RMF) 02
cleanall:: ; $(RMF) 05
cleanall:: ; $(RMF) 06
cleanall:: ; $(RMF) 07

######

###### end of [Makefile] ######
