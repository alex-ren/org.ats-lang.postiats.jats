/*
**
** The C code is generated by ATS/Postiats
** The starting compilation time is: 2014-10-29: 16h:42m
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
foo_0(atstkind_t0ype(atstype_int)) ;

ATSstatic()
atstkind_t0ype(atstype_int)
fclo1_1(atstkind_t0ype(atstype_int)) ;

ATSstatic()
atstkind_t0ype(atstype_int)
fclo2_2(atstkind_t0ype(atstype_int)) ;

/*
/home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/09.dats: 6(line=2, offs=5) -- 204(line=16, offs=4)
*/
/*
local: 
global: foo_0$0(level=0)
local: 
global: 
*/
ATSstatic()
atstkind_t0ype(atstype_int)
foo_0(atstkind_t0ype(atstype_int) arg0)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmpret0, atstkind_t0ype(atstype_int)) ;
ATStmpdec(tmp1, atstkind_t0ype(atstype_int)) ;
/* tmpvardeclst(end) */
ATSfunbody_beg()
/*
emit_instr: loc0 = /home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/09.dats: 6(line=2, offs=5) -- 204(line=16, offs=4)
*/
ATSINSflab(__patsflab_foo_0):
/*
emit_instr: loc0 = /home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/09.dats: 32(line=2, offs=31) -- 204(line=16, offs=4)
*/
/*
letpush(beg)
*/
/*
emit_instr: loc0 = /home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/09.dats: 46(line=3, offs=11) -- 54(line=3, offs=19)
*/
ATSINSmove(tmp1, PMVtmpltcstmat[0](g1int_add<S2Eextkind(atstype_int)>)(ATSPMVi0nt(99), ATSPMVi0nt(100))) ;

/*
letpush(end)
*/

/*
emit_instr: loc0 = /home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/09.dats: 198(line=15, offs=3) -- 200(line=15, offs=5)
*/
ATSINSmove(tmpret0, ATSPMVi0nt(44)) ;
/*
emit_instr: loc0 = /home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/09.dats: 32(line=2, offs=31) -- 204(line=16, offs=4)
*/
/*
INSletpop()
*/
ATSfunbody_end()
ATSreturn(tmpret0) ;
} /* end of [foo_0] */

/*
/home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/09.dats: 61(line=4, offs=7) -- 123(line=8, offs=6)
*/
/*
local: fclo2_2$0(level=1)
global: fclo1_1$0(level=1), fclo2_2$0(level=1)
local: x$65(1)(HSEapp(HSEcst(atstkind_t0ype); HSEs2exp(S2Eextkind(atstype_int))))
global: x$65(1)(HSEapp(HSEcst(atstkind_t0ype); HSEs2exp(S2Eextkind(atstype_int))))
*/
ATSstatic()
atstkind_t0ype(atstype_int)
fclo1_1(atstkind_t0ype(atstype_int) env0)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmpret2, atstkind_t0ype(atstype_int)) ;
ATStmpdec(tmp3, atstkind_t0ype(atstype_int)) ;
/* tmpvardeclst(end) */
ATSfunbody_beg()
/*
emit_instr: loc0 = /home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/09.dats: 61(line=4, offs=7) -- 123(line=8, offs=6)
*/
ATSINSflab(__patsflab_fclo1_1):
/*
emit_instr: loc0 = /home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/09.dats: 77(line=4, offs=23) -- 123(line=8, offs=6)
*/
/*
letpush(beg)
*/
/*
emit_instr: loc0 = /home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/09.dats: 93(line=5, offs=13) -- 99(line=5, offs=19)
*/
ATSINSmove(tmp3, PMVtmpltcstmat[0](g1int_add<S2Eextkind(atstype_int)>)(ATSPMVi0nt(33), env0)) ;

/*
letpush(end)
*/

/*
emit_instr: loc0 = /home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/09.dats: 109(line=7, offs=5) -- 117(line=7, offs=13)
*/
ATSINSmove(tmpret2, fclo2_2(env0)) ;

/*
emit_instr: loc0 = /home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/09.dats: 77(line=4, offs=23) -- 123(line=8, offs=6)
*/
/*
INSletpop()
*/
ATSfunbody_end()
ATSreturn(tmpret2) ;
} /* end of [fclo1_1] */

/*
/home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/09.dats: 130(line=9, offs=7) -- 192(line=13, offs=6)
*/
/*
local: fclo1_1$0(level=1)
global: fclo1_1$0(level=1), fclo2_2$0(level=1)
local: x$65(1)(HSEapp(HSEcst(atstkind_t0ype); HSEs2exp(S2Eextkind(atstype_int))))
global: x$65(1)(HSEapp(HSEcst(atstkind_t0ype); HSEs2exp(S2Eextkind(atstype_int))))
*/
ATSstatic()
atstkind_t0ype(atstype_int)
fclo2_2(atstkind_t0ype(atstype_int) env0)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmpret4, atstkind_t0ype(atstype_int)) ;
ATStmpdec(tmp5, atstkind_t0ype(atstype_int)) ;
/* tmpvardeclst(end) */
ATSfunbody_beg()
/*
emit_instr: loc0 = /home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/09.dats: 130(line=9, offs=7) -- 192(line=13, offs=6)
*/
ATSINSflab(__patsflab_fclo2_2):
/*
emit_instr: loc0 = /home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/09.dats: 146(line=9, offs=23) -- 192(line=13, offs=6)
*/
/*
letpush(beg)
*/
/*
emit_instr: loc0 = /home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/09.dats: 162(line=10, offs=13) -- 168(line=10, offs=19)
*/
ATSINSmove(tmp5, PMVtmpltcstmat[0](g1int_add<S2Eextkind(atstype_int)>)(ATSPMVi0nt(55), env0)) ;

/*
letpush(end)
*/

/*
emit_instr: loc0 = /home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/09.dats: 178(line=12, offs=5) -- 186(line=12, offs=13)
*/
ATSINSmove(tmpret4, fclo1_1(env0)) ;

/*
emit_instr: loc0 = /home/grad2/aren/workspace/Postiats/projects/utfpl/test/ats_testbed/09.dats: 146(line=9, offs=23) -- 192(line=13, offs=6)
*/
/*
INSletpop()
*/
ATSfunbody_end()
ATSreturn(tmpret4) ;
} /* end of [fclo2_2] */

/*
** for initialization(dynloading)
*/
ATSextern()
atsvoid_t0ype
_057_home_057_grad2_057_aren_057_workspace_057_Postiats_057_projects_057_utfpl_057_test_057_ats_testbed_057_09_056_dats__dynload()
{
ATSfunbody_beg()
ATSdynload(/*void*/)
ATSdynloadflag_ext(
_057_home_057_grad2_057_aren_057_workspace_057_Postiats_057_projects_057_utfpl_057_test_057_ats_testbed_057_09_056_dats__dynloadflag
) ;
ATSif(
ATSCKiseqz(
_057_home_057_grad2_057_aren_057_workspace_057_Postiats_057_projects_057_utfpl_057_test_057_ats_testbed_057_09_056_dats__dynloadflag
)
) ATSthen() {
ATSdynloadset(_057_home_057_grad2_057_aren_057_workspace_057_Postiats_057_projects_057_utfpl_057_test_057_ats_testbed_057_09_056_dats__dynloadflag) ;
/*
dynexnlst-initize(beg)
*/
/*
dynexnlst-initize(end)
*/
} /* ATSendif */
ATSfunbody_end()
ATSreturn_void(tmpret_void) ;
} /* end of [*_dynload] */

/* ****** ****** */

/* end-of-compilation-unit */
