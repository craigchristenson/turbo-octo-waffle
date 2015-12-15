class Client < ActiveRecord::Base

  def self.request(params)
    @url = API_URL
    @params = params.to_json
    @headers = {
      :accept => :json,
      :content_type => :json
    }

    @response = RestClient.post(@url, @params, @headers)
    JSON.parse(@response)
  end
end