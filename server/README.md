# Server

This is an example server application which will provide the API for the client,
authentication layer for the user, and handle all communication with the backend business server.

## Prerequisites

You will need the following things properly installed on your computer.

* [Git](http://git-scm.com/)
* [Ruby](https://www.ruby-lang.org/) (with Bundler)

## Installation

* `git clone <repository-url>` this repository
* change into the new directory
* `bundle install`
* `rake db:setup db:migrate db:seed`

## Running / Development

* `rails -s`
* Visit your app at [http://localhost:3000](http://localhost:3000).
