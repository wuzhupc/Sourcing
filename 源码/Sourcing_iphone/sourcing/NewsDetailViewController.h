//
//  NewsDetailViewController.h
//  sourcing
//
//  Created by wuzhu on 13-1-8.
//  Copyright (c) 2013年 wuzhu. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "BaseVO.h"
#import "FavoriteUtil.h"
#import "MBProgressHUD.h"

@interface NewsDetailViewController : UIViewController<UIWebViewDelegate,MBProgressHUDDelegate>
{
    NSString *_titleText;
    BaseVO *_baseVO;
    NSObject<FavDataChangeDelegate> *_favDelegate;
    MBProgressHUD *_HUD;
    BaseVO *_detailVO;
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
