import DS from 'ember-data';

export default Ember.Controller.extend({

  frill: Ember.Object.create({
    front: {
      defects: [Ember.Object.create({type: 'NONE'})]
    },
    rear: {
      defects: [Ember.Object.create({type: 'NONE'})]
    },
    hinge: {
      defects: [Ember.Object.create({type: 'NONE'})]
    },
    widgets: [
      {
        defects: [Ember.Object.create({type: 'NONE'})]
      }
    ]
  }),

  choices: Ember.String.w('NONE MINOR MAJOR MASSIVE'),

  result: { tier: '', generation: '' },

  actions: {
    selectItem: function (nothing, item) {
      // console.log(JSON.stringify(this.frill));
    },
    addFrontCasingDefect: function (item) {
      this.frill.front.defects.insertAt(-1, Ember.Object.create({type: 'NONE'}));
    },
    addRearCasingDefect: function (item) {
      this.frill.rear.defects.insertAt(-1, Ember.Object.create({type: 'NONE'}));
    },
    addHingeDefect: function (item) {
      this.frill.hinge.defects.insertAt(-1, Ember.Object.create({type: 'NONE'}));
    },
    addWidget: function (item) {
      this.frill.widgets.insertAt(-1, Ember.Object.create({defects: [{type: 'NONE'}]}));
    },
    addWidgetDefect: function (widget, defect) {
      this.frill.widgets[this.frill.widgets.indexOf(widget)].defects.push(Object.create({type: 'NONE'}));
    },
    removeFrontCasingDefect: function (item) {
      if (this.frill.front.defects.length > 1)
        this.frill.front.defects.removeObject(item);
    },
    removeRearCasingDefect: function (item) {
      if (this.frill.front.defects.length > 1)
        this.frill.rear.defects.removeObject(item);
    },
    removeHingeDefect: function (item) {
      if (this.frill.front.defects.length > 1)
        this.frill.hinge.defects.removeObject(item);
    },
    removeWidget: function (item) {
      if (this.frill.front.defects.length > 1)
        this.frill.widgets.removeObject(item);
    },
    removeWidgetDefect: function (widget, item) {
      if (this.frill.front.defects.length > 1)
        this.frill.widgets[this.frill.widgets.indexOf(widget)].defects.removeObject(item);
    },
    getTier: function () {
      var params = JSON.stringify({ frill: this.frill });

      Ember.$.ajax('http://localhost:3000/tier', {
        type: 'POST',
        contentType: 'application/json',
        data: params,
        context: this,
        success: function(data) {
          console.log(data);
          if (typeof(data.error) !== 'undefined') {
            alert(data.error.response);
          } else {
            Ember.set(this.result, 'tier', data.result.tier);
            Ember.set(this.result, 'generation', data.result.generation);
          }
        },
        error: function(error) {
          alert(error.message);
        }
      });
    }
  }
});
