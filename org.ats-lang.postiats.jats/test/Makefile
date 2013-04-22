#
#
# This Makefile is automatically generated
# from executing the following command-line:
#
# make -f Makefile.gen
#
#

######

CC=gcc -std=c99
PATSHOMEQ="$(PATSHOME)"
PATSOPT=$(PATSHOMEQ)/src/patsopt

######

all::

######

LNS=ln -s
RMF=rm -f

######
# $(call make-ats_program, program-name)
define make-ats_program
  all:: $1
  $1: $1.exe
  $1.exe: $1_dats.c ;   $(CC) -DATS_MEMALLOC_LIBC -I$(PATSHOMEQ) -I$(PATSHOMEQ)/ccomp/runtime -o $$@ $$<
  $1_dats.c: $1.dats ;   $(PATSOPT) -IATS$(PATSHOMEQ)/contrib --output $1_dats.c --dynamic $1.dats
  clean:: ; $(RMF) $1_dats.o
  clean:: ; $(RMF) $1_dats.c
  cleanall:: ; $(RMF) $1.exe

endef

$(eval $(call make-ats_program, atof))
$(eval $(call make-ats_program, f91))
$(eval $(call make-ats_program, fact))
$(eval $(call make-ats_program, fib))
$(eval $(call make-ats_program, temp))
$(eval $(call make-ats_program, test01))
$(eval $(call make-ats_program, test02))
$(eval $(call make-ats_program, test03))
$(eval $(call make-ats_program, test04))
$(eval $(call make-ats_program, gfib))
######

clean:: ; $(RMF) *~
clean:: ; $(RMF) *_?ats.o *_?ats.c
clean:: ; $(RMF) *_atxt.txt
clean:: ; $(RMF) *_atxt.dats
clean:: ; $(RMF) Makefile.exe

cleanall:: clean

###### end of [Makefile] ######
