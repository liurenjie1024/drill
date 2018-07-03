import collections
from jinja2 import Environment, FileSystemLoader, select_autoescape

env = Environment(loader=FileSystemLoader('.'), autoescape=select_autoescape(['html', 'xml']))

FuncMapping = collections.namedtuple('FuncMapping',
                                     ['name', 'scalar_func_id', 'drill_func_name', 'args'])

type_cats = {'BOOL': 1,
             'INT8': 2,
             'INT16': 2,
             'INT32': 2,
             'INT64': 2,
             'FLOAT4': 2,
             'FLOAT8': 2,
             'TIMESTAMP': 3,
             'STRING': 4
             }

type_pres = {'BOOL': 1,
             'INT8': 2,
             'INT16': 3,
             'INT32': 4,
             'INT64': 5,
             'FLOAT4': 6,
             'FLOAT8': 7,
             'TIMESTAMP': 8,
             'STRING': 9
             }

type_shorts = {'BOOL': 'BOOL',
               'INT8': 'INT8',
               'INT16': 'INT16',
               'INT32': 'INT32',
               'INT64': 'INT64',
               'FLOAT4': 'FLOAT4',
               'FLOAT8': 'FLOAT8',
               'STRING': 'STR'
               }

def write_functions_file(template_filename, output_filename, **contents):
    template = env.get_template(template_filename)
    with open(output_filename, 'w') as f:
        f.write(template.render(**contents))

def gen_scalar_operators(fn_names, supported_types, template_filename, output_filename):
    items = []
    imported_column_types = set()
    imported_scalar_func_ids = set()
    for fn_name in fn_names:
        for z_type1 in supported_types:
            for z_type2 in supported_types:
                cat1 = type_cats[z_type1]
                cat2 = type_cats[z_type2]
                if cat1 != cat2:
                    continue

                pre1 = type_pres[z_type1]
                pre2 = type_pres[z_type2]

                max_t = z_type1 if pre1 > pre2 else z_type2

                if max_t not in type_shorts:
                    continue

                scalar_func_id = f'{fn_names[fn_name]}_{type_shorts[max_t]}'
                imported_column_types.add(z_type1)
                imported_column_types.add(z_type2)
                imported_scalar_func_ids.add(scalar_func_id)
                items.append(
                    FuncMapping(f'{fn_name.upper()}_{z_type1}_{z_type2}',
                                scalar_func_id,
                                f'{fn_name}',
                                [f'{z_type1}', f'{z_type2}']))

    write_functions_file(template_filename, output_filename,
                         items=items, imported_column_types=imported_column_types,
                         imported_scalar_func_ids=imported_scalar_func_ids )

def gen_cmp_functions():
    fn_names = {'greater_than': 'GT',
                'greater_than_or_equal_to': 'GE',
                'less_than': 'LT',
                'less_than_or_equal_to': 'LE',
                'equal': 'EQ',
                'not_equal': 'NE'
                }

    supported_types = ['INT32', 'INT64', 'FLOAT4', 'FLOAT8', 'STRING']

    gen_scalar_operators(fn_names, supported_types, 'ComparatorFunctions.java',
                  '../src/main/java/io/github/zeus/expr/drill/ComparatorFunctions.java')


def gen_math_functions():
    fn_names = {'add': 'ADD',
                'subtract': 'MINUS',
                'multiply': 'MUL',
                'divide': 'DIV',
                }

    supported_types = ['INT32', 'INT64', 'FLOAT4', 'FLOAT8']


    gen_scalar_operators(fn_names, supported_types, 'MathFunctions.java',
                  '../src/main/java/io/github/zeus/expr/drill/MathFunctions.java')

def gen_agg_operators(fn_names, supported_types, template_filename, output_filename):
    items = []
    imported_column_types = set()
    imported_scalar_func_ids = set()
    for fn_name in fn_names:
        for z_type1 in supported_types:
            for z_type2 in supported_types:
                cat1 = type_cats[z_type1]
                cat2 = type_cats[z_type2]
                if cat1 != cat2:
                    continue

                pre1 = type_pres[z_type1]
                pre2 = type_pres[z_type2]

                max_t = z_type1 if pre1 > pre2 else z_type2

                if max_t not in type_shorts:
                    continue

                scalar_func_id = f'{fn_names[fn_name]}_{type_shorts[max_t]}'
                imported_column_types.add(z_type1)
                imported_column_types.add(z_type2)
                imported_scalar_func_ids.add(scalar_func_id)
                items.append(
                    FuncMapping(f'{fn_name.upper()}_{z_type1}_{z_type2}',
                                scalar_func_id,
                                f'{fn_name}',
                                [f'{z_type1}', f'{z_type2}']))

    write_functions_file(template_filename, output_filename,
                         items=items, imported_column_types=imported_column_types,
                         imported_scalar_func_ids=imported_scalar_func_ids )


gen_cmp_functions()
gen_math_functions()
