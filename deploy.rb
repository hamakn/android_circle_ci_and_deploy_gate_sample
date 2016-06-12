# deploy to DeployGate
# https://deploygate.com/docs/api

git_hash = `git rev-parse --short HEAD`

github = "https://github.com/hamakn/android_circle_ci_and_deploy_gate_sample"
circle_ci = "https://circleci.com/gh/hamakn/android_circle_ci_and_deploy_gate_sample"

deploy_gate_endpoint = "https://deploygate.com/api/users/hamakn/apps"
deploy_gate_options = [
  # API Key for deploy
  #{ key: "token", value: "${DEPLOY_GATE_API_KEY}" },
  { key: "token", value: "afd7f2ce5a43966dc2cbacda7df119ea4e0e8501" },
  # Binary to deploy
  { key: "file", value: "@app/build/outputs/apk/app-release-unsigned.apk" },

  # Optional: Push message
  #{ key: "message", value: "#{github}/tree/${GIT_HASH}" },
  { key: "message", value: "#{github}/tree/ebefbba" },
  # Optional: Key to update distribution page
  #{ key: "distribution_key", value: "${DEPLOY_GATE_APP_DISTRIBUTION_KEY}" },
  { key: "distribution_key", value: "564222ebe2e5bd032bd17f36a02bb490d27434fe" },
  # Optional: Release note
  #{ key: "release_note", value: "#{github}/tree/${GIT_HASH} #{circle_ci}/${CIRCLE_BUILD_NUM}" },
  { key: "release_note", value: "#{github}/tree/ebefbba #{circle_ci}/21" },
]

def deploy_gate_option(options)
  options.map { |option| "-F \"#{option[:key]}=#{option[:value]}\"" }.join(" ")
end

command = "curl #{deploy_gate_option(deploy_gate_options)} #{deploy_gate_endpoint}"
`#{command}`