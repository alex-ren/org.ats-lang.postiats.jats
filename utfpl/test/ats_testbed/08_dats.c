/*
**
** The C code is generated by ATS/Postiats
** The starting compilation time is: 2014-9-23: 12h:40m
**
*/

/*
** include runtime header files
*/
#ifndef _ATS_CCOMP_HEADER_NONE
#include "pats_ccomp_config.h"
#include "pats_ccomp_basics.h"
#include "pats_ccomp_typedefs.h"
#include "pats_ccomp_instrset.h"
#include "pats_ccomp_memalloc.h"
#ifndef _ATS_CCOMP_EXCEPTION_NONE
#include "pats_ccomp_memalloca.h"
#include "pats_ccomp_exception.h"
#endif // end of [_ATS_CCOMP_EXCEPTION_NONE]
#endif /* _ATS_CCOMP_HEADER_NONE */


/*
** include prelude cats files
*/
#ifndef _ATS_CCOMP_PRELUDE_NONE
//
#include "prelude/CATS/basics.cats"
#include "prelude/CATS/integer.cats"
#include "prelude/CATS/pointer.cats"
#include "prelude/CATS/bool.cats"
#include "prelude/CATS/char.cats"
#include "prelude/CATS/integer_ptr.cats"
#include "prelude/CATS/integer_fixed.cats"
#include "prelude/CATS/float.cats"
#include "prelude/CATS/memory.cats"
#include "prelude/CATS/string.cats"
#include "prelude/CATS/strptr.cats"
//
#include "prelude/CATS/filebas.cats"
//
#include "prelude/CATS/list.cats"
#include "prelude/CATS/option.cats"
#include "prelude/CATS/array.cats"
#include "prelude/CATS/arrayptr.cats"
#include "prelude/CATS/arrayref.cats"
#include "prelude/CATS/matrix.cats"
#include "prelude/CATS/matrixptr.cats"
//
#endif /* _ATS_CCOMP_PRELUDE_NONE */
/*
** for user-supplied prelude
*/
#ifdef _ATS_CCOMP_PRELUDE_USER
//
#include _ATS_CCOMP_PRELUDE_USER
//
#endif /* _ATS_CCOMP_PRELUDE_USER */

/*
staload-prologues(beg)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/pointer.dats: 1533(line=44, offs=1) -- 1572(line=44, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/integer.dats: 1636(line=51, offs=1) -- 1675(line=51, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/integer_fixed.dats: 1641(line=51, offs=1) -- 1680(line=51, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/memory.dats: 1410(line=38, offs=1) -- 1449(line=39, offs=32)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/string.dats: 1609(line=48, offs=1) -- 1648(line=48, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/strptr.dats: 1609(line=48, offs=1) -- 1648(line=48, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/strptr.dats: 1671(line=52, offs=1) -- 1718(line=52, offs=48)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/integer.dats: 1636(line=51, offs=1) -- 1675(line=51, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/filebas.dats: 1613(line=48, offs=1) -- 1652(line=48, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/filebas.dats: 1675(line=52, offs=1) -- 1722(line=52, offs=48)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/integer.dats: 1636(line=51, offs=1) -- 1675(line=51, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/filebas.dats: 1745(line=56, offs=1) -- 1783(line=56, offs=39)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/libc/SATS/stdio.sats: 1380(line=35, offs=1) -- 1418(line=37, offs=3)
*/

#include "libc/CATS/stdio.cats"
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/libc/SATS/stdio.sats: 1791(line=56, offs=1) -- 1833(line=58, offs=27)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/libc/sys/SATS/types.sats: 1390(line=36, offs=1) -- 1432(line=38, offs=3)
*/

#include "libc/sys/CATS/types.cats"
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/filebas.dats: 1861(line=61, offs=1) -- 1901(line=61, offs=41)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/libc/sys/SATS/stat.sats: 1390(line=36, offs=1) -- 1431(line=38, offs=3)
*/

#include "libc/sys/CATS/stat.cats"
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/libc/sys/SATS/stat.sats: 1776(line=53, offs=1) -- 1818(line=54, offs=35)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/libc/sys/SATS/types.sats: 1390(line=36, offs=1) -- 1432(line=38, offs=3)
*/

#include "libc/sys/CATS/types.cats"
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/filebas.dats: 15159(line=816, offs=1) -- 15189(line=816, offs=31)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/libc/SATS/stdio.sats: 1380(line=35, offs=1) -- 1418(line=37, offs=3)
*/

#include "libc/CATS/stdio.cats"
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/libc/SATS/stdio.sats: 1791(line=56, offs=1) -- 1833(line=58, offs=27)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/libc/sys/SATS/types.sats: 1390(line=36, offs=1) -- 1432(line=38, offs=3)
*/

#include "libc/sys/CATS/types.cats"
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/list.dats: 1527(line=44, offs=1) -- 1566(line=44, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/list.dats: 1567(line=45, offs=1) -- 1613(line=45, offs=47)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/unsafe.dats: 1532(line=44, offs=1) -- 1566(line=44, offs=35)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/list_vt.dats: 1536(line=44, offs=1) -- 1575(line=44, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/list_vt_mergesort.dats: 1546(line=44, offs=1) -- 1585(line=44, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/list_vt_quicksort.dats: 1546(line=44, offs=1) -- 1585(line=44, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/array.dats: 1534(line=44, offs=1) -- 1573(line=44, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/array.dats: 1574(line=45, offs=1) -- 1616(line=45, offs=43)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/array_bsearch.dats: 1531(line=44, offs=1) -- 1570(line=44, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/array_quicksort.dats: 1531(line=44, offs=1) -- 1570(line=44, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/arrayptr.dats: 1532(line=44, offs=1) -- 1571(line=44, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/arrayref.dats: 1532(line=44, offs=1) -- 1571(line=44, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/matrix.dats: 1535(line=44, offs=1) -- 1574(line=44, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/matrixptr.dats: 1538(line=44, offs=1) -- 1577(line=44, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/matrixref.dats: 1538(line=44, offs=1) -- 1577(line=44, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/stream.dats: 1570(line=48, offs=1) -- 1609(line=48, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/stream_vt.dats: 1573(line=48, offs=1) -- 1612(line=48, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/unsafe.dats: 1532(line=44, offs=1) -- 1566(line=44, offs=35)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/checkast.dats: 1531(line=44, offs=1) -- 1570(line=45, offs=32)
*/
/*
staload-prologues(end)
*/
/*
typedefs-for-tyrecs-and-tysums(beg)
*/
/*
typedefs-for-tyrecs-and-tysums(end)
*/
/*
dynconlst-declaration(beg)
*/
/*
dynconlst-declaration(end)
*/
/*
dyncstlst-declaration(beg)
*/
ATSdyncst_mac(atspre_g0int_add_int)
ATSdyncst_mac(atspre_print_string)
ATSdyncst_mac(atspre_print_int)
ATSdyncst_mac(atspre_print_newline)
/*
dyncstlst-declaration(end)
*/
/*
dynvalist-implementation(beg)
*/
/*
dynvalist-implementation(end)
*/
/*
exnconlst-declaration(beg)
*/
#ifndef _ATS_CCOMP_EXCEPTION_NONE
ATSextern()
atsvoid_t0ype
the_atsexncon_initize
(
  atstype_exnconptr d2c, atstype_string exnmsg
) ;
#endif // end of [_ATS_CCOMP_EXCEPTION_NONE]
/*
exnconlst-declaration(end)
*/
/*
assumelst-declaration(beg)
*/
/*
assumelst-declaration(end)
*/
/*
extypelst-declaration(beg)
*/
/*
extypelst-declaration(end)
*/
ATSstatic()
atstkind_t0ype(atstype_int)
foo0_0() ;

ATSstatic()
atstkind_t0ype(atstype_int)
foo_1(atstkind_t0ype(atstype_int), atstkind_t0ype(atstype_int)) ;

#if(0)
ATSextern()
atsvoid_t0ype
mainats_void_0() ;
#endif // end of [QUALIFIED]

/*
/home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/08.dats: 90(line=6, offs=5) -- 195(line=12, offs=4)
*/
/*
local: 
global: foo0_0$0(level=0)
local: 
global: 
*/
ATSstatic()
atstkind_t0ype(atstype_int)
foo0_0()
{
/* tmpvardeclst(beg) */
ATStmpdec(tmpret0, atstkind_t0ype(atstype_int)) ;
/* tmpvardeclst(end) */
ATSfunbody_beg()
/*
emit_instr: loc0 = /home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/08.dats: 90(line=6, offs=5) -- 195(line=12, offs=4)
*/
ATSINSflab(__patsflab_foo0_0):
/*
emit_instr: loc0 = /home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/08.dats: 105(line=6, offs=20) -- 195(line=12, offs=4)
*/
/*
letpush(beg)
*/
/* (*nothing*) */
/*
letpush(end)
*/

/*
emit_instr: loc0 = /home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/08.dats: 175(line=9, offs=13) -- 181(line=9, offs=19)
*/
ATSINSmove(tmpret0, foo_1(ATSPMVi0nt(3), ATSPMVi0nt(1))) ;

/*
emit_instr: loc0 = /home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/08.dats: 105(line=6, offs=20) -- 195(line=12, offs=4)
*/
/*
INSletpop()
*/
ATSfunbody_end()
ATSreturn(tmpret0) ;
} /* end of [foo0_0] */

/*
/home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/08.dats: 127(line=8, offs=7) -- 162(line=8, offs=42)
*/
/*
local: 
global: foo_1$0(level=1)
local: z$3449(1)(HSEapp(HSEcst(atstkind_t0ype); HSEs2exp(S2Eextkind(atstype_int))))
global: z$3449(1)(HSEapp(HSEcst(atstkind_t0ype); HSEs2exp(S2Eextkind(atstype_int))))
*/
ATSstatic()
atstkind_t0ype(atstype_int)
foo_1(atstkind_t0ype(atstype_int) env0, atstkind_t0ype(atstype_int) arg0)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmpret1, atstkind_t0ype(atstype_int)) ;
ATStmpdec(tmp2, atstkind_t0ype(atstype_int)) ;
/* tmpvardeclst(end) */
ATSfunbody_beg()
/*
emit_instr: loc0 = /home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/08.dats: 127(line=8, offs=7) -- 162(line=8, offs=42)
*/
ATSINSflab(__patsflab_foo_1):
/*
emit_instr: loc0 = /home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/08.dats: 153(line=8, offs=33) -- 158(line=8, offs=38)
*/
ATSINSmove(tmp2, atspre_g0int_add_int(ATSPMVi0nt(3), arg0)) ;

/*
emit_instr: loc0 = /home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/08.dats: 153(line=8, offs=33) -- 162(line=8, offs=42)
*/
ATSINSmove(tmpret1, atspre_g0int_add_int(tmp2, env0)) ;

ATSfunbody_end()
ATSreturn(tmpret1) ;
} /* end of [foo_1] */

/*
/home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/08.dats: 213(line=14, offs=17) -- 269(line=17, offs=4)
*/
/*
local: foo0_0$0(level=0)
global: foo0_0$0(level=0), mainats_void_0$3$0(level=0)
local: 
global: 
*/
ATSextern()
atsvoid_t0ype
mainats_void_0()
{
/* tmpvardeclst(beg) */
ATStmpdec_void(tmpret3) ;
ATStmpdec_void(tmp4) ;
ATStmpdec_void(tmp5) ;
ATStmpdec(tmp6, atstkind_t0ype(atstype_int)) ;
/* tmpvardeclst(end) */
ATSfunbody_beg()
/*
emit_instr: loc0 = /home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/08.dats: 207(line=14, offs=11) -- 269(line=17, offs=4)
*/
ATSINSflab(__patsflab_main_void_0):
/*
emit_instr: loc0 = /home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/08.dats: 218(line=14, offs=22) -- 269(line=17, offs=4)
*/
/*
letpush(beg)
*/
/*
letpush(end)
*/

/*
emit_instr: loc0 = /home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/08.dats: 233(line=15, offs=12) -- 262(line=15, offs=41)
*/
ATSINSmove_void(tmp4, atspre_print_string(ATSPMVstring("ret is "))) ;

/*
emit_instr: loc0 = /home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/08.dats: 254(line=15, offs=33) -- 261(line=15, offs=40)
*/
ATSINSmove(tmp6, foo0_0()) ;

/*
emit_instr: loc0 = /home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/08.dats: 233(line=15, offs=12) -- 262(line=15, offs=41)
*/
ATSINSmove_void(tmp5, atspre_print_int(tmp6)) ;

/*
emit_instr: loc0 = /home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/08.dats: 233(line=15, offs=12) -- 262(line=15, offs=41)
*/
ATSINSmove_void(tmpret3, atspre_print_newline()) ;

/*
emit_instr: loc0 = /home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/08.dats: 218(line=14, offs=22) -- 269(line=17, offs=4)
*/
/*
INSletpop()
*/
ATSfunbody_end()
ATSreturn_void(tmpret3) ;
} /* end of [mainats_void_0] */

/*
** for initialization(dynloading)
*/
ATSdynloadflag_init(_057_home_057_grad2_057_aren_057_workspace_057_Postiats_057_projects_057_utfpl_057_test_057_ats_testbed_057_08_056_dats__dynloadflag) ;
ATSextern()
atsvoid_t0ype
_057_home_057_grad2_057_aren_057_workspace_057_Postiats_057_projects_057_utfpl_057_test_057_ats_testbed_057_08_056_dats__dynload()
{
ATSfunbody_beg()
ATSdynload(/*void*/)
ATSdynloadflag_sta(
_057_home_057_grad2_057_aren_057_workspace_057_Postiats_057_projects_057_utfpl_057_test_057_ats_testbed_057_08_056_dats__dynloadflag
) ;
ATSif(
ATSCKiseqz(
_057_home_057_grad2_057_aren_057_workspace_057_Postiats_057_projects_057_utfpl_057_test_057_ats_testbed_057_08_056_dats__dynloadflag
)
) ATSthen() {
ATSdynloadset(_057_home_057_grad2_057_aren_057_workspace_057_Postiats_057_projects_057_utfpl_057_test_057_ats_testbed_057_08_056_dats__dynloadflag) ;
/*
dynexnlst-initize(beg)
*/
/*
dynexnlst-initize(end)
*/
/* (*nothing*) */
} /* ATSendif */
ATSfunbody_end()
ATSreturn_void(tmpret_void) ;
} /* end of [*_dynload] */

/*
** the ATS runtime
*/
#ifndef _ATS_CCOMP_RUNTIME_NONE
#include "pats_ccomp_runtime.c"
#include "pats_ccomp_runtime_memalloc.c"
#ifndef _ATS_CCOMP_EXCEPTION_NONE
#include "pats_ccomp_runtime2_dats.c"
#ifndef _ATS_CCOMP_RUNTIME_TRYWITH_NONE
#include "pats_ccomp_runtime_trywith.c"
#endif /* _ATS_CCOMP_RUNTIME_TRYWITH_NONE */
#endif // end of [_ATS_CCOMP_EXCEPTION_NONE]
#endif /* _ATS_CCOMP_RUNTIME_NONE */

/*
** the [main] implementation
*/
int
main
(
int argc, char **argv, char **envp
) {
int err = 0 ;
_057_home_057_grad2_057_aren_057_workspace_057_Postiats_057_projects_057_utfpl_057_test_057_ats_testbed_057_08_056_dats__dynload() ;
ATSmainats_void_0(err) ;
return (err) ;
} /* end of [main] */

/* ****** ****** */

/* end-of-compilation-unit */