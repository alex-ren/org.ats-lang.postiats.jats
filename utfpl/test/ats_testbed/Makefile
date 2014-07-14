
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

######


######

RMF=rm -f

######

clean:: ; $(RMF) *~
clean:: ; $(RMF) *_?ats.o
clean:: ; $(RMF) *_?ats.c

cleanall:: clean
cleanall:: ; $(RMF) 02

######

###### end of [Makefile] ######
