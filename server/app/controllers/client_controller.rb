class ClientController < ApplicationController
  # before_filter :authenticate_user_from_token!

  respond_to :json

  def tier
    result = Client.request(frill_params)
    render json: { result: result }
  end

  def frill_params
    params.require(:frill).delete_if { |key, value| value.blank? }
  end
end
