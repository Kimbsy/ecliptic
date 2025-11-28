// Minimal forward declarations to satisfy the C++ compiler for uLisp single-file port.

typedef struct sobject object;
typedef uint32_t symbol_t;
typedef uint32_t builtin_t;
typedef int (*gfun_t)();

object *symbol (symbol_t name);
object *intern (symbol_t name);
object *lispstring (char *s);
object *apply (object *function, object *args, object *env);
object *eval (object *form, object *env);
object *findpair (object *var, object *env);
char *lookupdoc (builtin_t name);
char *cstring (object *form, char *buffer, int buflen);
object *tf_progn (object *args, object *env);
object *fn_princtostring (object *args, object *env);
object *readmain (gfun_t gfun);
object *read (gfun_t gfun);
