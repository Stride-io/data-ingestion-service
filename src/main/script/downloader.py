import requests
import kaggle
import random

def get_priority(search_result):
    metadata = vars(search_result)
    if metadata['usabilityRating'] < 0.5:
        return 0
    return metadata['usabilityRating']+ (100*metadata['voteCount']) + (10*metadata['downloadCount'])

def get_random_words(num_words):
    api_url = "https://random-word-api.herokuapp.com/word"
    params = {
        "number": num_words,
        "length": random.randint(1, 10)
    }

    response = requests.get(api_url, params=params)
    response.raise_for_status()
    return response.json()


if __name__ == "__main__":
    random_words = get_random_words(10)
    while len(random_words) == 0:
        random_words = get_random_words(10)
    for word in random_words:
        print("word = ", word)
        search_results = kaggle.api.dataset_list(search = word)
        print("search_results = ", search_results)
        if len(search_results) == 0:
            continue
        sorted_results = sorted(search_results, key=get_priority)
        if len(sorted_results):
            kaggle.api.dataset_download_files(
                dataset = sorted_results[0].ref,
                path='/Users/pinaksawhney/Desktop/Project/kaggle_test_datat',
                unzip=True
            )
            break
