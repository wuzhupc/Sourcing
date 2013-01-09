//
//  NewsDetailViewController.h
//  sourcing
//
//  Created by wuzhu on 13-1-8.
//  Copyright (c) 2013年 wuzhu. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "BaseVO.h"
#import "BaseServiceDelegate.h"
#import "FavoriteUtil.h"

@interface NewsDetailViewController : UIViewController<BaseServiceDelegate>
{
    NSString *_titleText;
    BaseVO *_baseVO;
    NSObject<FavDataChangeDelegate> *_favDelegate;
}

-(id)initWithBaseVO:(BaseVO *)kvo title:(NSString *)ktitle;

@property (weak, nonatomic) IBOutlet UIButton *buttonFav;
@property (weak, nonatomic) IBOutlet UIButton *buttonShare;
@property (weak, nonatomic) IBOutlet UIButton *buttonReturn;
@property (weak, nonatomic) IBOutlet UIWebView *webviewDetail;
@property (weak, nonatomic) IBOutlet UILabel *labelTitle;

- (IBAction)actionReturn:(id)sender;
- (IBAction)actionFav:(id)sender;
- (IBAction)actionShare:(id)sender;

@end
