import Ember from 'ember';

export default Ember.Controller.extend({
  session: Ember.inject.service('session'),

  actions: {
    invalidateSession() {
      var token = this.get('session').get('data.authenticated').token;
      this.get('session').invalidate(token);
    }
  }
});
