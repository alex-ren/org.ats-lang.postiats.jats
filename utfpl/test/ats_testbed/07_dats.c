/*
**
** The C code is generated by ATS/Postiats
** The compilation time is: 2014-7-15: 15h:49m
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
staload-prologues(end)
*/
/*
typedefs-for-tyrecs-and-tysums(beg)
*/
typedef
struct {
atstkind_t0ype(atstype_int) atslab__1; 
atstkind_t0ype(atstype_int) atslab__2; 
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
atstype_boxed
foo_0(atstkind_t0ype(atstype_int)) ;

ATSstaticdec()
atstkind_t0ype(atstype_int)
foo1_1() ;

#if(0)
ATSglobaldec()
atsvoid_t0ype
mainats_void_0() ;
#endif // end of [QUALIFIED]

/*
/home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/07.dats: 79(line=8, offs=5) -- 199(line=12, offs=4)
*/
/*
local: 
global: foo_0$0(level=0)
local: 
global: 
*/
ATSstaticdec()
atstype_boxed
foo_0(atstkind_t0ype(atstype_int) arg0)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmpret0, atstype_boxed) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
/*
emit_instr: loc0 = /home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/07.dats: 79(line=8, offs=5) -- 199(line=12, offs=4)
*/
__patsflab_foo_0:
/*
emit_instr: loc0 = /home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/07.dats: 183(line=11, offs=3) -- 195(line=11, offs=15)
*/
ATSINSmove_boxrec(tmpret0, postiats_tyrec_0) ;
ATSINSstore_boxrec_ofs (tmpret0, postiats_tyrec_0, atslab__1, ATSPMVi0nt(1)) ;
ATSINSstore_boxrec_ofs (tmpret0, postiats_tyrec_0, atslab__2, ATSPMVi0nt(2)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmpret0) ;
} /* end of [foo_0] */

/*
/home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/07.dats: 205(line=14, offs=5) -- 264(line=18, offs=4)
*/
/*
local: foo_0$0(level=0)
global: foo_0$0(level=0), foo1_1$0(level=0)
local: 
global: 
*/
ATSstaticdec()
atstkind_t0ype(atstype_int)
foo1_1()
{
/* tmpvardeclst(beg) */
ATStmpdec(tmpret1, atstkind_t0ype(atstype_int)) ;
ATStmpdec(tmp2, atstype_boxed) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
/*
emit_instr: loc0 = /home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/07.dats: 205(line=14, offs=5) -- 264(line=18, offs=4)
*/
__patsflab_foo1_1:
/*
emit_instr: loc0 = /home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/07.dats: 220(line=14, offs=20) -- 264(line=18, offs=4)
*/
/*
letpush(beg)
*/
/*
emit_instr: loc0 = /home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/07.dats: 245(line=15, offs=22) -- 252(line=15, offs=29)
*/
ATSINSmove(tmp2, foo_0(ATSPMVi0nt(22))) ;

/*
letpush(end)
*/

/*
emit_instr: loc0 = /home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/07.dats: 259(line=17, offs=3) -- 260(line=17, offs=4)
*/
ATSINSmove(tmpret1, ATSPMVi0nt(1)) ;
/*
emit_instr: loc0 = /home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/07.dats: 220(line=14, offs=20) -- 264(line=18, offs=4)
*/
/*
INSletpop()
*/
/* funbodyinstrlst(end) */
ATSreturn(tmpret1) ;
} /* end of [foo1_1] */

/*
/home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/07.dats: 282(line=20, offs=17) -- 347(line=24, offs=4)
*/
/*
local: foo1_1$0(level=0)
global: foo_0$0(level=0), foo1_1$0(level=0), mainats_void_0$2$0(level=0)
local: 
global: 
*/
ATSglobaldec()
atsvoid_t0ype
mainats_void_0()
{
/* tmpvardeclst(beg) */
ATStmpdec_void(tmpret3, atsvoid_t0ype) ;
ATStmpdec(tmp4, atstkind_t0ype(atstype_int)) ;
ATStmpdec_void(tmp5, atsvoid_t0ype) ;
ATStmpdec_void(tmp6, atsvoid_t0ype) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
/*
emit_instr: loc0 = /home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/07.dats: 276(line=20, offs=11) -- 347(line=24, offs=4)
*/
__patsflab_main_void_0:
/*
emit_instr: loc0 = /home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/07.dats: 287(line=20, offs=22) -- 347(line=24, offs=4)
*/
/*
letpush(beg)
*/
/*
emit_instr: loc0 = /home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/07.dats: 301(line=21, offs=11) -- 308(line=21, offs=18)
*/
ATSINSmove(tmp4, foo1_1()) ;

/*
letpush(end)
*/

/*
emit_instr: loc0 = /home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/07.dats: 320(line=22, offs=12) -- 340(line=22, offs=32)
*/
ATSINSmove_void(tmp5, atspre_print_string(ATSPMVstring("x = "))) ;

/*
emit_instr: loc0 = /home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/07.dats: 320(line=22, offs=12) -- 340(line=22, offs=32)
*/
ATSINSmove_void(tmp6, atspre_print_int(tmp4)) ;

/*
emit_instr: loc0 = /home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/07.dats: 320(line=22, offs=12) -- 340(line=22, offs=32)
*/
ATSINSmove_void(tmpret3, atspre_print_newline()) ;

/*
emit_instr: loc0 = /home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/07.dats: 287(line=20, offs=22) -- 347(line=24, offs=4)
*/
/*
INSletpop()
*/
/* funbodyinstrlst(end) */
ATSreturn_void(tmpret3) ;
} /* end of [mainats_void_0] */

/*
** for initialization(dynloading)
*/
atsvoid_t0ype
_057_home_057_grad2_057_aren_057_workspace_057_Postiats_057_projects_057_utfpl_057_test_057_ats_testbed_057_07_056_dats__dynload()
{
ATSdynload0(
_057_home_057_grad2_057_aren_057_workspace_057_Postiats_057_projects_057_utfpl_057_test_057_ats_testbed_057_07_056_dats__dynloadflag
) ;
ATSif(
ATSCKiseqz(
_057_home_057_grad2_057_aren_057_workspace_057_Postiats_057_projects_057_utfpl_057_test_057_ats_testbed_057_07_056_dats__dynloadflag
)
) ATSthen() {
ATSdynloadset(_057_home_057_grad2_057_aren_057_workspace_057_Postiats_057_projects_057_utfpl_057_test_057_ats_testbed_057_07_056_dats__dynloadflag) ;
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
_057_home_057_grad2_057_aren_057_workspace_057_Postiats_057_projects_057_utfpl_057_test_057_ats_testbed_057_07_056_dats__dynload() ;
ATSmainats_void_0(err) ;
return (err) ;
} /* end of [main] */
