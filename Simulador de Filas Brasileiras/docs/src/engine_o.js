const engine = {
  groups: {},
  queues: {},
  outputText: '',

  parse(input) {
    const lines = input.trim().split('\n');
    return lines.map(line => line.trim().split(' '));
  },

  output(text) {
    this.outputText += text + '\n';
  },

  exec(command) {
    const action = command[0];

    if (action === 'grupo:') this.execNewGroup(command);
    else if (action === 'existe:') this.output(this.execExists(command));
    else if (action === 'conhece:') this.output(this.execKnow(command));
    else if (action === 'criaFila:') this.execNewQueue(command);
    else if (action === 'atendeFila:') {
      for (let i = 1; i < command.length; i++) this.execServeQueue(command[i]);
    }
    else if (action === 'chegou:') {
      for (let i = 1; i < command.length; i++) this.execArrived(command[i]);
    }
    else if (action === 'desiste:') {
      for (let i = 1; i < command.length; i++) this.execGiveUp(command[i]);
    }
    else if (action === 'imprime:') this.print();
  },

  execNewGroup(command) {
    const people = command.slice(1);
    const groupId = 'grupo' + (Object.keys(this.groups).length + 1);
    this.groups[groupId] = people;
  },

  execExists(command) {
    const name = command[1];
    for (const group of Object.values(this.groups)) {
      if (group.includes(name)) {
        return `[${name}] existe!`;
      }
    }
    return `[${name}] NÃO existe!`;
  },

  execKnow(command) {
    const [_, name1, name2] = command;
    for (const group of Object.values(this.groups)) {
      if (group.includes(name1) && group.includes(name2)) {
        return `[${name1}] conhece [${name2}]`;
      }
    }
    return `[${name1}] NÃO conhece [${name2}]`;
  },

  execNewQueue(command) {
    const id = command[1];
    this.queues[id] = [];
  },

  execServeQueue(queueId) {
    if (this.queues[queueId] && this.queues[queueId].length > 0) {
      this.queues[queueId].shift();
    }
  },

  execArrived(name) {
    let bestQueue = null;
    let bestIndex = Infinity;

    for (const [queueId, queue] of Object.entries(this.queues)) {
      let insertIndex = queue.length;

      for (let i = 0; i < queue.length; i++) {
        if (this.knows(name, queue[i])) {
          insertIndex = i + 1;
        }
      }

      if (
        insertIndex < bestIndex ||
        (insertIndex === bestIndex && queue.length < bestQueue?.length)
      ) {
        bestQueue = queue;
        bestIndex = insertIndex;
      }
    }

    if (bestQueue) {
      bestQueue.splice(bestIndex, 0, name);
    }
  },

  knows(name1, name2) {
    for (const group of Object.values(this.groups)) {
      if (group.includes(name1) && group.includes(name2)) {
        return true;
      }
    }
    return false;
  },

  execGiveUp(name) {
    for (const queue of Object.values(this.queues)) {
      const index = queue.indexOf(name);
      if (index >= 0) {
        queue.splice(index, 1);
        break;
      }
    }
  },

  print() {
    for (const [id, queue] of Object.entries(this.queues)) {
      const people = queue.join(', ');
      this.output(`#${id} [ ${people} ]`);
    }
  },

  run(commands) {
    this.groups = {};
    this.queues = {};
    this.outputText = '';
    for (const command of commands) {
      this.exec(command);
    }
  }
};

function processar() {
  const fileInput = document.getElementById('inputFile');
  const outputEl = document.getElementById('saida');

  const file = fileInput.files[0];
  if (!file) {
    alert('Por favor, selecione um arquivo de entrada.');
    return;
  }

  const reader = new FileReader();
  reader.onload = function(e) {
    const text = e.target.result;
    const comandos = engine.parse(text);
    engine.run(comandos);
    outputEl.textContent = engine.outputText;
  };
  reader.readAsText(file);
}
