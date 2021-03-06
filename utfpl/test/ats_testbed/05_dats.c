/*
**
** The C code is generated by ATS/Postiats
** The compilation time is: 2014-7-15: 11h:56m
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
typedef
struct {
atstkind_t0ype(atstype_int) atslab__0; 
atstkind_t0ype(atstype_int) atslab__1; 
} postiats_tyrec_0 ;
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
extern void the_atsexncon_initize (atstype_exncon *d2c, char *exnmsg) ;
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
ATSstaticdec()
postiats_tyrec_0
foo2_0() ;

#if(0)
ATSglobaldec()
atsvoid_t0ype
mainats_void_0() ;
#endif // end of [QUALIFIED]

/*
/home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/05.dats: 89(line=5, offs=5) -- 126(line=5, offs=42)
*/
/*
local: 
global: foo2_0$0(level=0)
local: 
global: 
*/
ATSstaticdec()
postiats_tyrec_0
foo2_0()
{
/* tmpvardeclst(beg) */
ATStmpdec(tmpret0, postiats_tyrec_0) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
/*
emit_instr: loc0 = /home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/05.dats: 89(line=5, offs=5) -- 126(line=5, offs=42)
*/
__patsflab_foo2_0:
/*
emit_instr: loc0 = /home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/05.dats: 120(line=5, offs=36) -- 126(line=5, offs=42)
*/
ATSINSstore_fltrec_ofs (tmpret0, postiats_tyrec_0, atslab__0, ATSPMVi0nt(1)) ;
ATSINSstore_fltrec_ofs (tmpret0, postiats_tyrec_0, atslab__1, ATSPMVi0nt(2)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmpret0) ;
} /* end of [foo2_0] */

/*
/home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/05.dats: 144(line=7, offs=17) -- 278(line=13, offs=4)
*/
/*
local: 
global: mainats_void_0$1$0(level=0)
local: 
global: 
*/
ATSglobaldec()
atsvoid_t0ype
mainats_void_0()
{
/* tmpvardeclst(beg) */
ATStmpdec_void(tmpret1, atsvoid_t0ype) ;
ATStmpdec(tmp2, atstkind_t0ype(atstype_int)) ;
ATStmpdec_void(tmp3, atsvoid_t0ype) ;
ATStmpdec_void(tmp4, atsvoid_t0ype) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
/*
emit_instr: loc0 = /home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/05.dats: 138(line=7, offs=11) -- 278(line=13, offs=4)
*/
__patsflab_main_void_0:
/*
emit_instr: loc0 = /home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/05.dats: 149(line=7, offs=22) -- 278(line=13, offs=4)
*/
/*
letpush(beg)
*/
/*
emit_instr: loc0 = /home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/05.dats: 191(line=9, offs=11) -- 202(line=9, offs=22)
*/
ATSINSmove(tmp2, ATSfunclo_fun(PMVerr("/home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/05.dats: 191(line=9, offs=11) -- 195(line=9, offs=15)"), (atstkind_t0ype(atstype_int), atstkind_t0ype(atstype_int)), atstkind_t0ype(atstype_int))(ATSPMVi0nt(1), ATSPMVi0nt(2))) ;

/*
letpush(end)
*/

/*
emit_instr: loc0 = /home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/05.dats: 250(line=11, offs=12) -- 271(line=11, offs=33)
*/
ATSINSmove_void(tmp3, atspre_print_string(ATSPMVstring("y is "))) ;

/*
emit_instr: loc0 = /home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/05.dats: 250(line=11, offs=12) -- 271(line=11, offs=33)
*/
ATSINSmove_void(tmp4, atspre_print_int(tmp2)) ;

/*
emit_instr: loc0 = /home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/05.dats: 250(line=11, offs=12) -- 271(line=11, offs=33)
*/
ATSINSmove_void(tmpret1, atspre_print_newline()) ;

/*
emit_instr: loc0 = /home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/05.dats: 149(line=7, offs=22) -- 278(line=13, offs=4)
*/
/*
INSletpop()
*/
/* funbodyinstrlst(end) */
ATSreturn_void(tmpret1) ;
} /* end of [mainats_void_0] */

/*
** for initialization(dynloading)
*/
atsvoid_t0ype
_057_home_057_grad2_057_aren_057_workspace_057_Postiats_057_projects_057_utfpl_057_test_057_ats_testbed_057_05_056_dats__dynload()
{
ATSdynload0(
_057_home_057_grad2_057_aren_057_workspace_057_Postiats_057_projects_057_utfpl_057_test_057_ats_testbed_057_05_056_dats__dynloadflag
) ;
ATSif(
ATSCKiseqz(
_057_home_057_grad2_057_aren_057_workspace_057_Postiats_057_projects_057_utfpl_057_test_057_ats_testbed_057_05_056_dats__dynloadflag
)
) ATSthen() {
ATSdynloadset(_057_home_057_grad2_057_aren_057_workspace_057_Postiats_057_projects_057_utfpl_057_test_057_ats_testbed_057_05_056_dats__dynloadflag) ;
/*
dynexnlst-initize(beg)
*/
/*
dynexnlst-initize(end)
*/
} /* ATSendif */
ATSreturn_void() ;
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
_057_home_057_grad2_057_aren_057_workspace_057_Postiats_057_projects_057_utfpl_057_test_057_ats_testbed_057_05_056_dats__dynload() ;
ATSmainats_void_0(err) ;
return (err) ;
} /* end of [main] */
