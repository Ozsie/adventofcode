with open("input/2020/day16", "r") as file:
    data = file.read()
    my_ticket = [int(x) for x in data.split('your ticket:')[1].split('nearby tickets:')[0].strip('\n').split(',')]
    nearby_tickets = [[int(x) for x in y.split(',')] for y in data.split('nearby tickets:')[1].strip('\n').split('\n')]
    raw_rules = {x.split(':')[0] : x for x in data.split('your ticket:')[0].strip('\n').split('\n')}
    rules = {y.split(':')[0] : (lambda x, val : int(x.split(': ')[1].split(' or ')[0].split('-')[0]) <= val <= int(x.split(': ')[1].split(' or ')[0].split('-')[1])   \
                                             or int(x.split(': ')[1].split(' or ')[1].split('-')[0]) <= val <= int(x.split(': ')[1].split(' or ')[1].split('-')[1]))  \
             for y in data.split('your ticket:')[0].strip('\n').split('\n')}
    invalids = [[x for x in row if not any([rules[rule](raw_rules[rule],x) for rule in rules.keys()])] for row in nearby_tickets]
    print('Part 1: {}'.format(sum([sum([x for x in y]) for y in invalids])))
    valids = [nearby_tickets[i] for i in range(len(nearby_tickets)) if not invalids[i]]
    combinations = {rule:[i for i in range(len(valids[0])) if all([rules[rule](raw_rules[rule],row[i]) for row in valids])] for rule in rules.keys()}
    indexes, final_combinations = [], {}
    for x,y in sorted(combinations.items(), key=lambda item: len(item[1])):
        for z in y:
            if z in indexes:
                continue
            else:
                indexes.append(z)
                final_combinations[x] = z
                break
    print('Part 2: {}'.format(reduce(lambda x,y: x*y, [my_ticket[final_combinations[z]] for z in final_combinations.keys() if z.startswith('departure')])))